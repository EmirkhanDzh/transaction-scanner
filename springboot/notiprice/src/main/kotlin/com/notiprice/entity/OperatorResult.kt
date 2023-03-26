package com.notiprice.entity

import com.notiprice.dto.OperatorResultDto

data class OperatorResult(
    override val id: Long = 0,
    override val transactionId: Long = 0,
    override val isClear: Boolean = true,
    val operatorId: Long? = null,
    val isClearByOperator: Boolean = true,
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
    override fun toDto() = OperatorResultDto(
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