package com.notiprice.dao

import com.notiprice.entity.OperatorResult
import com.notiprice.entity.RulesEngineResult

interface ResultDao {
    fun getRulesEngineCheckingResult(transactionId: Long): RulesEngineResult

    fun getOperatorCheckingResult(transactionId: Long): OperatorResult
}