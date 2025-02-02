package com.transaction.scanner.dao

import com.transaction.scanner.entity.Operator
import org.springframework.data.jpa.repository.JpaRepository

interface OperatorRepository: JpaRepository<Operator, Long> {
    fun findByOperatorUsername(operatorPassword: String?): Operator?
}