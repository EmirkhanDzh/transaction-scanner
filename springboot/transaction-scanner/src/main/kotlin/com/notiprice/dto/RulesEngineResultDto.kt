package com.notiprice.dto

data class RulesEngineResultDto(
    var id: Long? = null,
    var isClear: Boolean? = null,
    var sanctionDto: SanctionDto? = null,
)
