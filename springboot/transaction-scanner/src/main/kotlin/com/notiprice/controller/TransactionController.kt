package com.notiprice.controller

import com.notiprice.dao.OperatorRepository
import com.notiprice.dao.OperatorsResultRepository
import com.notiprice.extension.toDto
import com.notiprice.extension.toEntity
import com.notiprice.dao.TransactionRepository
import com.notiprice.dto.*
import com.notiprice.entity.OperatorsResult
//import com.notiprice.service.TransactionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class TransactionController(
//    private val transactionService: TransactionService,
    private val transactionRepository: TransactionRepository,
    private val operatorsResultRepository: OperatorsResultRepository,
    private val operatorRepository: OperatorRepository,
) {

    val dbListOfTransactions = mutableListOf(
        TransactionDto(
            id = 1,
            clientFrom = "Ivan Petrov",
            clientTo = "Nikita Ivanov",
            amount = 47L,
            bankFrom = "Tinkoff",
            bankTo = "VTB",
            paySystemFrom = "Visa",
            paySystemTo = "Mastercard",
            transferDate = LocalDateTime.of(2023, 3, 30, 22, 24),
            countryFrom = "Russia",
            countryTo = "Russia",
            operatorResult = OperatorResultDto(
                id = 1,
                operator = OperatorDto(
                    id = 1,
                    username = "operator_1",
                    password = "pwd_1",
                ),
                rulesEngineResult = RulesEngineResultDto(
                    id = 1,
                    isClear = false,
                    sanction = SanctionDto(
                        id = 1,
                        type = "ClientSanctionList",
                        value = "Ivan Petrov",
                        description = "Client in the sanction list of USA",
                    )
                ),
                isClear = null,
                comment = null,
            )
        ),
        TransactionDto(
            id = 2,
            clientFrom = "Anna Smirnova",
            clientTo = "Ekaterina Ivanova",
            amount = 23L,
            bankFrom = "Tinkoff",
            bankTo = "KICB",
            paySystemFrom = "Mir",
            paySystemTo = "Visa",
            transferDate = LocalDateTime.of(2023, 3, 30, 22, 54),
            countryFrom = "Russia",
            countryTo = "Kyrgyzstan",
            operatorResult = OperatorResultDto(
                id = 2,
                operator = OperatorDto(
                    id = 1,
                    username = "operator_1",
                    password = "pwd_1",
                ),
                rulesEngineResult = RulesEngineResultDto(
                    id = 2,
                    isClear = false,
                    sanction = SanctionDto(
                        id = 2,
                        type = "BankSanctionList",
                        value = "KICB",
                        description = "Bank in the sanction list of World Bank Group",
                    )
                ),
                isClear = null,
                comment = null,
            )
        ),
        TransactionDto(
            id = 3,
            clientFrom = "Timur Gagarin",
            clientTo = "Oleg Popov ",
            amount = 85L,
            bankFrom = "Rosbank",
            bankTo = "Tinkoff",
            paySystemFrom = "Mastercard",
            paySystemTo = "Mir",
            transferDate = LocalDateTime.of(2023, 3, 30, 20, 48),
            countryFrom = "Russia",
            countryTo = "Russia",
            operatorResult = OperatorResultDto(
                id = 3,
                operator = OperatorDto(
                    id = 1,
                    username = "operator_1",
                    password = "pwd_1",
                ),
                rulesEngineResult = RulesEngineResultDto(
                    id = 3,
                    isClear = false,
                    sanction = SanctionDto(
                        id = 3,
                        type = "PaySystemSanctionList",
                        value = "Mir",
                        description = "PaySystem in the sanction list of England",
                    )
                ),
                isClear = true,
                comment = "Mir is Russia's official PaySystem",
            )
        ),
        TransactionDto(
            id = 4,
            clientFrom = "Anna Smirnova",
            clientTo = "Timur Gagarin",
            amount = 23L,
            bankFrom = "Optbank",
            bankTo = "BapeshBank",
            paySystemFrom = "Mir",
            paySystemTo = "Maestro",
            transferDate = LocalDateTime.of(2023, 3, 27, 17, 13),
            countryFrom = "Russia",
            countryTo = "Tajikistan",
            operatorResult = OperatorResultDto(
                id = 4,
                operator = OperatorDto(
                    id = 1,
                    username = "operator_1",
                    password = "pwd_1",
                ),
                rulesEngineResult = RulesEngineResultDto(
                    id = 4,
                    isClear = false,
                    sanction = SanctionDto(
                        id = 4,
                        type = "CountrySanctionList",
                        value = "Tajikistan",
                        description = "Country in the sanction list of EU",
                    )
                ),
                isClear = false,
                comment = "Yes, Tajikistan in the sanction list of EU",
            )
        )
    )

    // возвращать сущности вместо айдишников
    @GetMapping("/transaction/all")
    fun getTransactions(@RequestParam operatorId: Long, @RequestParam isChecked: Boolean): List<TransactionDto> {

        return transactionRepository
            .findAllByOperatorResult_OperatorId(operatorId)
            .filter {
                if (isChecked) {
                    it.operatorResult?.rulesEngineResult != null && checkNotNull(it.operatorResult).isClear != null
                } else {
                    it.operatorResult?.rulesEngineResult != null && checkNotNull(it.operatorResult).isClear == null
                }
            }
            .map { it.toDto() }

    }

    @GetMapping("/transaction/{transactionId}")
    fun getTransaction(@PathVariable transactionId: Long): TransactionDto? =
        transactionRepository.findById(transactionId).orElse(null)?.toDto()

    // сохранение решения от оператора
    @PutMapping("/transaction/complete-operator-result")
    fun completeOperatorResult(@RequestBody dto: CompleteOperatorResultRequestDto) {

        val result = operatorsResultRepository.findById(requireNotNull(dto.operatorResultId)).orElse(null)
        requireNotNull(result) { "There is no operatorResult with id = ${dto.operatorResultId}" }

        result.comment = dto.comment
        result.isClear = dto.isClear

        operatorsResultRepository.save(result)
    }

    /**
     * контроллер, который обрабатывает отправку транзакций со стороны пользователя
     */
    @PostMapping("/transaction/save")
    fun saveTransactions(@RequestBody transactionList: List<TransactionDto>) {

        val transactions = transactionList.map { it.toEntity() }

        transactions.forEach {
            check(it.operatorResult == null) { "Operator result shouldn't have been set" }

            it.operatorResult = OperatorsResult().apply {
                operator = operatorRepository.findById(1).get()
            }
        }

        transactionRepository.saveAll(transactions)
    }
}
