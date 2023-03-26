package com.notiprice.dao

import com.notiprice.entity.Paysystem
import com.notiprice.entity.RulesEngineResult

interface RulesEngineResultDao {
    fun findByIdOrNull(id: Long): RulesEngineResult?
}