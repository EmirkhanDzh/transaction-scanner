package com.notiprice.dto

import com.notiprice.entity.RulesEngineResult

data class RulesEngineResultDto(
    val id: Long = 0,
    val transactionId: Long = 0,
    val isClear: Boolean = true,
    val clientSanctionId: Long? = null,
    val bankSanctionId: Long? = null,
    val paysystemSanctionId: Long? = null,
    val countrySanctionId: Long? = null,
) {
    fun toEntity() = RulesEngineResult(
        id,
        transactionId,
        isClear,
        clientSanctionId,
        bankSanctionId,
        paysystemSanctionId,
        countrySanctionId,
    )
}
