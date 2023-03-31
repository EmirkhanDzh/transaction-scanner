package com.notiprice.dto

data class OperatorResultDto(
    var id: Long? = null,
    var operator: OperatorDto? = null,
    var rulesEngineResult: RulesEngineResultDto? = null,
    var isClear: Boolean? = null,
    var comment: String? = null,
)

