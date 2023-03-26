package com.notiprice.dto

import com.notiprice.entity.Bank
import com.notiprice.entity.BankClient
import java.time.LocalDate

data class BankClientDto(
    val id: Long = 0,
    val name: String? = null,
    val patronymic: String? = null,
    val birthDay: LocalDate? = null,
    val phoneNumber: String? = null,
    val citizenshipCountryId: Long = 0,
) {
    fun toEntity() = BankClient(
        id,
        name,
        patronymic,
        birthDay,
        phoneNumber,
        citizenshipCountryId,
    )

}