package com.notiprice.entity

import com.notiprice.dto.SanctionDto

/**
 * Сущность хранит описание санкции для конкретной сущности.
 * Из этой сущности мы достаем информацию для комментария по санкционной транзакции.
 */
data class Sanction(
    val id: Long = 0,
    val code: String? = null,
    val description: String? = null,
    /**
     * Идентификатор экзмепляра одной из 4-х сущностей: банк, клиент банка, страна, платежная система
     */
    val entity_id: Long? = null,
) {
//    fun toDto() = SanctionDto(id, code, description, entity_id)
}