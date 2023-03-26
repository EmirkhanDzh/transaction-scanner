package com.notiprice.entity

import com.notiprice.dto.CountryDto

data class Country(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
) {
    fun toDto() = CountryDto(id, name, code)
}
