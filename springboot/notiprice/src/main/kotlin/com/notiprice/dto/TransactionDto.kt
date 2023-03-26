package com.notiprice.dto

import com.notiprice.entity.Transaction
import com.notiprice.entity.User
import java.math.BigDecimal
import java.time.LocalDateTime

class TransactionDto(
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
    fun toEntity() = Transaction(
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