package com.transaction.scanner.dto

data class RulesEngineResultDto(
    var id: Long? = null,
    var isClear: Boolean? = null,
    var sanction: SanctionDto? = null,
)
