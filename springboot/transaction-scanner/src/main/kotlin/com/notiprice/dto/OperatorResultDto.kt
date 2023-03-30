package com.notiprice.dto

data class OperatorResultDto(
    var id: Long? = null,
    var operatorId: Long? = null,
    var rulesEngineResultDto: RulesEngineResultDto? = null,
    var isClear: Boolean? = null,
    var comment: String? = null,
)

