package com.notiprice.dao;

import com.notiprice.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findAllByOperatorResult_OperatorId(operatorId: Long): List<Transaction>
//    fun findAllById
}