package com.notiprice.dto

import com.notiprice.entity.OperatorResult


data class OperatorResultDto(
    var id: Long? = null,
    var operatorId: Long? = null,
    var rulesEngineResultDto: RulesEngineResultDto? = null,
    var isClear: Boolean? = null,
    var comment: String? = null,
) {
//    fun toEntity() = OperatorResult(
//        id,
//        transactionId,
//        isClear,
//        operatorId,
//        isClearByOperator,
//        comment,
//    )
}

