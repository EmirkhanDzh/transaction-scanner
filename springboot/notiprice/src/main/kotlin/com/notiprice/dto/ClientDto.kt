package com.notiprice.dto

import com.notiprice.entity.Client
import com.notiprice.entity.Country
import java.time.LocalDate

data class ClientDto(
    val id: Long = 0,
    val name: String? = null,
    val patronymic: String? = null,
    val birthDay: LocalDate? = null,
    val phoneNumber: String? = null,
    val citizenshipCountry: CountryDto? = null,
) {
    fun toEntity() = Client(
        id,
        name,
        patronymic,
        birthDay,
        phoneNumber,
        citizenshipCountry?.toEntity(),
    )

}