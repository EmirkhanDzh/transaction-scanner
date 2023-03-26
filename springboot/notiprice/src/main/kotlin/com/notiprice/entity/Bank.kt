package com.notiprice.entity

import com.notiprice.dto.BankDto

data class Bank(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
    val countryId: Long = 0,
) {
    fun toDto() = BankDto(id, name, code, countryId)
}
