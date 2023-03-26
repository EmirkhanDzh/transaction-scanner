package com.notiprice.controller

import com.notiprice.dto.TransactionDto
import com.notiprice.service.TransactionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.Path

@RestController
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping("/transaction/all-transactions/{userId}")
    fun getTransactions(@PathVariable userId: Long): List<TransactionDto> =
        transactionService.getAllTransactionByUserId(userId)

    /**
     * контроллер, который обрабатывает отправку транзакций со стороны пользователя
     */
    @PostMapping("/transaction/analyse")
    fun analyseTransactions(@RequestBody transactionList: List<TransactionDto>) =
        transactionService.saveAndAnalyseTransactionList(transactionList)

    @GetMapping("/transaction/{transactionId}")
    fun getTransaction(@PathVariable transactionId: Long) =
        transactionService.getTransaction(transactionId)?.toDto()

    @GetMapping("/transaction/{transactionId}/engine-checking-result")
    fun getTransactionRulesEngineCheckingResult(@PathVariable transactionId: Long) =
        transactionService.getTransactionRulesCheckingResult(transactionId)?.toDto()

    @GetMapping("/transaction/{transactionId}/operator-checking-result")
    fun getTransactionOperatorCheckingResult(@PathVariable transactionId: Long) =
        transactionService.getTransactionOperatorCheckingResult(transactionId)?.toDto()
}
