package com.notiprice.dto

import com.notiprice.entity.Bank

data class BankDto(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
    val countryId: Long = 0,
) {
    fun toEntity()= Bank(
        id,
        name,
        code,
        countryId,
    )
}
