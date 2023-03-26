package com.notiprice.dto

import com.notiprice.entity.Paysystem

data class PaysystemDto(
    val name: String? = null,
    val code: String? = null,
    val countryId: Long = 0,
) {
    fun toEntity() = Paysystem(name, code, countryId)
}
