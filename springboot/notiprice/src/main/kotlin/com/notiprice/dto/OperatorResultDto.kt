package com.notiprice.dto

import com.notiprice.entity.OperatorResult


data class OperatorResultDto(
    override val id: Long = 0,
    override val transactionId: Long = 0,
    override val isClear: Boolean = true,
    val operatorId: Long? = null,
    val isClearByOperator: Boolean = true,
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
    override fun toEntity() = OperatorResult(
        id,
        transactionId,
        isClear,
        operatorId,
        isClearByOperator,
        clientSanctionId,
        bankSanctionId,
        paysystemSanctionId,
        countrySanctionId,
    )
}

