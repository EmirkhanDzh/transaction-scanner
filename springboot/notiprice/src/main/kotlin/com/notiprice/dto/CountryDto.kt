package com.notiprice.dto

import com.notiprice.entity.Bank
import com.notiprice.entity.Country

data class CountryDto(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
) {
    fun toEntity() = Country(
        id,
        name,
        code,
    )
}