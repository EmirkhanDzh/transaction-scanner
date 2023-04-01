package com.notiprice.dao

import com.notiprice.entity.Operator
import org.springframework.data.jpa.repository.JpaRepository

interface OperatorRepository: JpaRepository<Operator, Long> {
    fun findByOperatorUsername(operatorPassword: String?): Operator?
}