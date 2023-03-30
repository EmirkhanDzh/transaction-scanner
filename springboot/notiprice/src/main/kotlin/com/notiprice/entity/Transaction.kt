package com.notiprice.entity

import com.notiprice.dto.TransactionDto
import java.time.LocalDateTime

data class Transaction(
    val id: Long = 0,
    val clientFrom: Client? = null,
    val clientTo: Client? = null,
    val amount: Long? = null,
    val currency: String? = null,
    val bankFrom: Bank? = null,
    val bankTo: Bank? = null,
    val paySystemFrom: Paysystem? = null,
    val paySystemTo: Paysystem? = null,
    val transferDate: LocalDateTime? = null,
    val cityFrom: String? = null,
    val cityTo: String? = null,
    val countryFrom: Country? = null,
    val countryTo: Country? = null,
    val rulesEngineResult: RulesEngineResult? = null,
    val operatorResult: OperatorResult? = null,
) {
//    fun toDto() = TransactionDto(
//        id,
//        clientFrom?.toDto(),
//        clientTo?.toDto(),
//        amount,
//        currency,
//        bankFrom?.toDto(),
//        bankTo?.toDto(),
//        paySystemFrom?.toDto(),
//        paySystemTo?.toDto(),
//        transferDate,
//        cityFrom,
//        cityTo,
//        countryFrom?.toDto(),
//        countryTo?.toDto(),
//        rulesEngineResult?.toDto(),
//        operatorResult?.toDto(),
//    )
}