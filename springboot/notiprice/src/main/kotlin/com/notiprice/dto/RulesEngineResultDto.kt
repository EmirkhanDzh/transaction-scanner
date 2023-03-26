package com.notiprice.dto

import com.notiprice.entity.RulesEngineResult

data class RulesEngineResultDto(
    override val id: Long = 0,
    override val transactionId: Long = 0,
    override val isClear: Boolean = true,
    override val clientSanctionId: Long? = null,
    override val bankSanctionId: Long? = null,
    override val paysystemSanctionId: Long? = null,
    override val countrySanctionId: Long? = null,
) : ResultDto(
    id,
    transactionId,
    isClear,
    clientSanctionId,
    bankSanctionId,
    paysystemSanctionId,
    countrySanctionId,
) {
    override fun toEntity() = RulesEngineResult(
        id,
        transactionId,
        isClear,
        clientSanctionId,
        bankSanctionId,
        paysystemSanctionId,
        countrySanctionId,
    )
}
