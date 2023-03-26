package com.notiprice.entity

import com.notiprice.dto.TransactionDto
import java.time.LocalDateTime

data class Transaction(
    val id: Long = 0,
    val clientIdFrom: Long? = null,
    val clientIdTo: Long? = null,
    val amount: Long? = null,
    val currency: String? = "RUB",
    val bankFrom: String? = "TNKF",
    val bankTo: String? = null,
    val paySystemFrom: String? = "MIR",
    val paySystemTo: String? = null,
    val transferDate: LocalDateTime? = null,
    val cityCodeFrom: String? = null,
    val cityCodeTo: String? = null,
    val countryCodeFrom: String? = "RU",
    val countryCodeTo: String? = null,
    val rulesEngineResultId: Long? = null,
    val operatorResultId: Long? = null,
) {
    fun toDto() = TransactionDto(
        id,
        clientIdFrom,
        clientIdTo,
        amount,
        currency,
        bankFrom,
        bankTo,
        paySystemFrom,
        paySystemTo,
        transferDate,
        cityCodeFrom,
        cityCodeTo,
        countryCodeFrom,
        countryCodeTo,
        rulesEngineResultId,
        operatorResultId,
    )
}