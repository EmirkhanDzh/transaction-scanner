package com.notiprice.dto

import java.time.LocalDateTime

data class TransactionDto(
    var id: Long? = null,
    var clientFrom: String? = null,
    var clientTo: String? = null,
    var amount: Long? = null,
    var bankFrom: String? = null,
    var bankTo: String? = null,
    var paySystemFrom: String? = null,
    var paySystemTo: String? = null,
    var transferDate: LocalDateTime? = null,
    var countryFrom: String? = null,
    var countryTo: String? = null,
    var operatorResult: OperatorResultDto? = null,
)