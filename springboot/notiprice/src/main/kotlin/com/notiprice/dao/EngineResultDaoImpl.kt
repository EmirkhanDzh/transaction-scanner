package com.notiprice.dao

import com.notiprice.entity.OperatorResult
import com.notiprice.entity.RulesEngineResult
import org.springframework.stereotype.Component

@Component
class ResultDaoImpl: ResultDao {
    override fun getRulesEngineCheckingResult(transactionId: Long): RulesEngineResult {
        TODO("Not yet implemented")
    }

    override fun getOperatorCheckingResult(transactionId: Long): OperatorResult {
        TODO("Not yet implemented")
    }
}