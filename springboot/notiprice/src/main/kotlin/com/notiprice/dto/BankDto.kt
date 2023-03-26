package com.notiprice.dto

import com.notiprice.entity.Bank
import com.notiprice.entity.Country

data class BankDto(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
    val country: Country? = null,
) {
    fun toEntity()= Bank(
        id,
        name,
        code,
        country,
    )
}
