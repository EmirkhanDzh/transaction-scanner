package com.notiprice.entity

import com.notiprice.dto.PaysystemDto

data class Paysystem(
    val name: String? = null,
    val code: String? = null,
    val countryId: Long = 0,
) {
    fun toDto() = PaysystemDto(name, code, countryId)
}
