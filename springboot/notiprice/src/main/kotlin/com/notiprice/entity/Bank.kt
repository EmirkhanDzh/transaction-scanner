package com.notiprice.entity

import com.notiprice.dto.BankDto

data class Bank(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
    val country: Country? = null,
) {
    fun toDto() = BankDto(id, name, code, country)
}
