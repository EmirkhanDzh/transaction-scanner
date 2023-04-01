package com.transaction.scanner.controller

import com.transaction.scanner.dto.*
import com.transaction.scanner.service.TransactionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val transactionService: TransactionService,
) {

    @GetMapping("/all")
    fun getTransactions(@RequestParam operatorId: Long, @RequestParam isChecked: Boolean): List<TransactionDto> =
        transactionService.getTransactions(operatorId, isChecked)

    @GetMapping("/{transactionId}")
    fun getTransaction(@PathVariable transactionId: Long): TransactionDto? =
        transactionService.getTransaction(transactionId)

    /**
     * сохранение решения от оператора
     */
    @PutMapping("/complete-operator-result")
    fun completeOperatorResult(@RequestBody dto: CompleteOperatorResultRequestDto) =
        transactionService.completeOperatorResult(dto)

    /**
     * обрабатывает отправку транзакций со стороны пользователя
     */
    @PostMapping("/save")
    fun saveTransactions(@RequestParam operatorId: Long, @RequestBody transactionList: List<TransactionDto>) =
        transactionService.saveTransactions(operatorId, transactionList)
}
