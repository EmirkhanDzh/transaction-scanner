package com.notiprice.entity

import com.notiprice.dto.BankClientDto
import java.time.LocalDate

data class BankClient(
    val id: Long = 0,
    val name: String? = null,
    val patronymic: String? = null,
    val birthDay: LocalDate? = null,
    val phoneNumber: String? = null,
    val citizenshipCountryId: Long = 0,
) {
    fun toDto() = BankClientDto(id, name, patronymic, birthDay, phoneNumber, citizenshipCountryId)
}
