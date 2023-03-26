package com.notiprice.dao

import com.notiprice.entity.OperatorResult
import com.notiprice.entity.Paysystem

interface OperatorResultDao {
    fun findByIdOrNull(id: Long): OperatorResult?
}