package com.notiprice.dao

import com.notiprice.entity.Transaction

interface TransactionDao {
    fun save(transaction: Transaction): Transaction

    fun findByIdOrNull(id: Long): Transaction?

    fun getAllByUserId(userId: Long): List<Transaction>

    fun update(transaction: Transaction): Int

    fun delete(id: Long): Int
}