package com.notiprice.entity

import com.notiprice.dto.ClientDto
import java.time.LocalDate

data class Client(
    val id: Long = 0,
    val name: String? = null,
    val patronymic: String? = null,
    val birthDay: LocalDate? = null,
    val phoneNumber: String? = null,
    val citizenshipCountry: Country? = null,
) {
    fun toDto() = ClientDto(
        id,
        name,
        patronymic,
        birthDay,
        phoneNumber,
        citizenshipCountry?.toDto(),
    )
}
