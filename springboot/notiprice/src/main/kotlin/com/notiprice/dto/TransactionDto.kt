package com.notiprice.dto

import com.notiprice.entity.Bank
import com.notiprice.entity.Transaction
import java.time.LocalDateTime

class TransactionDto(
    val id: Long = 0,
    val clientFrom: ClientDto? = null,
    val clientTo: ClientDto? = null,
    val amount: Long? = null,
    val currency: String? = null,
    val bankFrom: BankDto? = null,
    val bankTo: BankDto? = null,
    val paySystemFrom: PaysystemDto? = null,
    val paySystemTo: PaysystemDto? = null,
    val transferDate: LocalDateTime? = null,
    val cityFrom: String? = null,
    val cityTo: String? = null,
    val countryFrom: CountryDto? = null,
    val countryTo: CountryDto? = null,
    val rulesEngineResult: RulesEngineResultDto? = null,
    val operatorResult: OperatorResultDto? = null,
) {
    fun toEntity() = Transaction(
        id,
        clientFrom?.toEntity(),
        clientTo?.toEntity(),
        amount,
        currency,
        bankFrom?.toEntity(),
        bankTo?.toEntity(),
        paySystemFrom?.toEntity(),
        paySystemTo?.toEntity(),
        transferDate,
        cityFrom,
        cityTo,
        countryFrom?.toEntity(),
        countryTo?.toEntity(),
        rulesEngineResult?.toEntity(),
        operatorResult?.toEntity(),
    )
}