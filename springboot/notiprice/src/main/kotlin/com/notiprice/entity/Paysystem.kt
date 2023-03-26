package com.notiprice.entity

import com.notiprice.dto.PaysystemDto

data class Paysystem(
    val name: String? = null,
    val code: String? = null,
    val country: Country? = null,
) {
    fun toDto() = PaysystemDto(name, code, country?.toDto())
}
