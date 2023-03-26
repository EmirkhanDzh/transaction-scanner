package com.notiprice.controller

import com.notiprice.service.TransactionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping("/transaction/all-transactions/{userId}")
    fun getTransactionsList(@PathVariable userId: Long) {
        transactionService.getAllTransactionsByUserId(userId)
    }

}