package com.notiprice.dto

import com.notiprice.entity.Country
import com.notiprice.entity.Paysystem

data class PaysystemDto(
    val name: String? = null,
    val code: String? = null,
    val country: CountryDto? = null,
) {
    fun toEntity() = Paysystem(name, code, country?.toEntity())
}
