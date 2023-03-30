package com.notiprice.entity

import com.notiprice.dto.OperatorResultDto

data class OperatorResult(
    val id: Long = 0,
    val transactionId: Long = 0,
    val isClear: Boolean = true,
    val operatorId: Long? = null,
    val isClearByOperator: Boolean = true,
    val comment: String? = null,
) {
//    fun toDto() = OperatorResultDto(
//        id,
//        transactionId,
//        isClear,
//        operatorId,
//        isClearByOperator,
//        comment,
//    )
}