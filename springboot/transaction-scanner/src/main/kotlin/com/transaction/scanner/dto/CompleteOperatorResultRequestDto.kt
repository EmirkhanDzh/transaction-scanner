package com.transaction.scanner.dto


data class CompleteOperatorResultRequestDto(
    var operatorResultId: Long? = null,
    var isClear: Boolean? = null,
    var comment: String? = null,
)

