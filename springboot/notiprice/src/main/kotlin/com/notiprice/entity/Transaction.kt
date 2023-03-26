package com.notiprice.entity

data class Transaction(
    val id: String? = null,
    val fromClientId: String? = null,
    val toClientId: String? = null,
    val amount: String? = null,
    val currency: String? = "RUB",
    val bankFrom: String? = "TNKF",
    val bankTo: String? = null,
    val paySystemFrom: String? = "MIR",
    val paySystemTo: String? = null,
    val transferDate: String? = null,
    val cityFrom: String? = null,
    val cityTo: String? = null,
    val countryCodeFrom: String? = "RU",
    val countryCodeTo: String? = null,
    val resultId: String? = null,
)