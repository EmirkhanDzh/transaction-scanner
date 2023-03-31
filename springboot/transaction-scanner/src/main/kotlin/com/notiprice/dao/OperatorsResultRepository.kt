package com.notiprice.dao;

import com.notiprice.entity.OperatorsResult
import org.springframework.data.jpa.repository.JpaRepository

interface OperatorsResultRepository : JpaRepository<OperatorsResult, Long> {
}