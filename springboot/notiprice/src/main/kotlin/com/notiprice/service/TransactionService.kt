package com.notiprice.service

import com.notiprice.dao.TransactionDaoImpl
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionDao: TransactionDaoImpl
) {

    fun getAllTransactionsByUserId(userId: Long) = transactionDao.getAllByUserId(userId)
}