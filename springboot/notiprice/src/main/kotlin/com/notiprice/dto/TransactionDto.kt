package com.notiprice.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
    var id: Long = 0,
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
) {
//    fun toEntity() = Transaction(
//        id,
//        clientFrom?
//        clientTo,
//        amount,
//        currency,
//        bankFrom?.toEntity(),
//        bankTo?.toEntity(),
//        paySystemFrom?.toEntity(),
//        paySystemTo?.toEntity(),
//        transferDate,
//        cityFrom,
//        cityTo,
//        countryFrom?.toEntity(),
//        countryTo?.toEntity(),
//        rulesEngineResult?.toEntity(),
//        operatorResult?.toEntity(),
//    )
}