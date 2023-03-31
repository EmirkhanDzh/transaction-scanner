package com.notiprice.controller

import com.notiprice.dao.TransactionRepository
import com.notiprice.dto.*
import com.notiprice.entity.OperatorsResult
import com.notiprice.entity.Transaction
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
                operatorId = 1,
                rulesEngineResultDto = RulesEngineResultDto(
                    id = 1,
                    isClear = false,
                    sanctionDto = SanctionDto(
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
                operatorId = 1,
                rulesEngineResultDto = RulesEngineResultDto(
                    id = 2,
                    isClear = false,
                    sanctionDto = SanctionDto(
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
                operatorId = 1,
                rulesEngineResultDto = RulesEngineResultDto(
                    id = 3,
                    isClear = false,
                    sanctionDto = SanctionDto(
                        id = 3,
                        type = "paySystemSanctionList",
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
                operatorId = 1,
                rulesEngineResultDto = RulesEngineResultDto(
                    id = 4,
                    isClear = false,
                    sanctionDto = SanctionDto(
                        id = 4,
                        type = "countrySanctionList",
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
    fun getTransactions(@RequestParam userId: Long, @RequestParam isChecked: Boolean): List<TransactionDto> =
        if (isChecked) {
            dbListOfTransactions.filter { it.operatorResult?.rulesEngineResultDto != null && checkNotNull(it.operatorResult).isClear != null }
        } else {
            dbListOfTransactions.filter { it.operatorResult?.rulesEngineResultDto != null && checkNotNull(it.operatorResult).isClear == null }
        }
//        transactionService.getAllTransactionByUserId(userId)

    @GetMapping("/transaction/{transactionId}")
    fun getTransaction(@PathVariable transactionId: Long): TransactionDto? =
        dbListOfTransactions.find { it.id == transactionId }
//        transactionService.getTransaction(transactionId)?.toDto()

    // сохранение решения от оператора
    // подумать над редактированием уже принятого решения от оператора
    @PutMapping("/transaction/complete-operator-result")
    fun completeOperatorResult(@RequestBody dto: CompleteOperatorResultRequestDto) {
        dbListOfTransactions.find { checkNotNull(it.operatorResult).id == dto.operatorResultId }?.apply {
            operatorResult?.isClear = dto.isClear
            operatorResult?.comment = dto.comment
        }
//        transactionService.createOperatorResult(operatorResultDto)
    }

    /**
     * контроллер, который обрабатывает отправку транзакций со стороны пользователя
     */
    @PostMapping("/transaction/save")
    fun analyseTransactions(@RequestBody transactionList: List<TransactionDto>) {

        transactionList.forEach {
            check(it.operatorResult == null) { "Operator result shouldn't have been set" }
            it.operatorResult = OperatorResultDto(
                id = null,
                operatorId = 1,
            )
        }

        dbListOfTransactions.addAll(transactionList)

        transactionRepository.saveAll(transactionList.map {
            Transaction().apply {
                id = 4
                clientFrom = it.clientFrom
                clientTo = it.clientTo
                amount = it.amount
                bankFrom = it.bankFrom
                bankTo = it.bankTo
                paySystemFrom = it.paySystemFrom
                paySystemTo = it.paySystemTo
                transferDate = it.transferDate
                countryFrom = it.countryFrom
                countryTo = it.countryTo
            }
        })

        // transactionService.saveAndAnalyseTransactionList(transactionList)
    }


//    @GetMapping("/transaction/{transactionId}/engine-checking-result")
//    fun getTransactionRulesEngineCheckingResult(@PathVariable transactionId: Long) =
//        transactionService.getTransactionRulesCheckingResult(transactionId)?.toDto()
//
//    @GetMapping("/transaction/{transactionId}/operator-checking-result")
//    fun getTransactionOperatorCheckingResult(@PathVariable transactionId: Long) =
//        transactionService.getTransactionOperatorCheckingResult(transactionId)?.toDto()
}
