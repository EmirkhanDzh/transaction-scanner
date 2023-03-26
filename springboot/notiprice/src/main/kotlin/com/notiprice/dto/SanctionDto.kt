package com.notiprice.dto

import com.notiprice.entity.Sanction

/**
 * Сущность хранит описание санкции для конкретной сущности.
 * Из этой сущности мы достаем информацию для комментария по санкционной транзакции.
 */
data class SanctionDto(
    val id: Long = 0,
    val code: String? = null,
    val description: String? = null,
    /**
     * Идентификатор экзмепляра одной из 4-х сущностей: банк, клиент банка, страна, платежная система
     */
    val entity_id: Long? = null,
) {
    fun toEntity() = Sanction(
        id,
        code,
        description,
        entity_id,
    )
}