package com.transaction.scanner.dao;

import com.transaction.scanner.entity.OperatorsResult
import org.springframework.data.jpa.repository.JpaRepository

interface OperatorsResultRepository : JpaRepository<OperatorsResult, Long> {
}