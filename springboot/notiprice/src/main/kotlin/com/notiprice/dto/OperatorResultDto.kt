package com.notiprice.dto

import com.notiprice.entity.OperatorResult


data class OperatorResultDto(
    val id: Long = 0,
    val transactionId: Long = 0,
    val isClear: Boolean = true,
    val operatorId: Long? = null,
    val isClearByOperator: Boolean = true,
    val comment: String? = null,
) {
    fun toEntity() = OperatorResult(
        id,
        transactionId,
        isClear,
        operatorId,
        isClearByOperator,
        comment,
    )
}

