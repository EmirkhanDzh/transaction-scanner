package com.notiprice.entity

import com.notiprice.dto.RulesEngineResultDto

data class RulesEngineResult(
    override val id: Long = 0,
    override val transactionId: Long = 0,
    override val isClear: Boolean = true,
    override val clientSanctionId: Long? = null,
    override val bankSanctionId: Long? = null,
    override val paysystemSanctionId: Long? = null,
    override val countrySanctionId: Long? = null,
) : Result(
    id,
    transactionId,
    isClear,
    clientSanctionId,
    bankSanctionId,
    paysystemSanctionId,
    countrySanctionId,
) {
    override fun toDto() = RulesEngineResultDto(
        id,
        transactionId,
        isClear,
        clientSanctionId,
        bankSanctionId,
        paysystemSanctionId,
        countrySanctionId,
    )
}