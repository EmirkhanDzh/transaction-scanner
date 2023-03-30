package com.notiprice.dto

import com.notiprice.entity.Sanction

/**
 * Сущность хранит описание санкции для конкретной сущности.
 * Из этой сущности мы достаем информацию для комментария по санкционной транзакции.
 */
data class SanctionDto(
    var id: Long? = null,
    var type: String? = null,
    var value: String? = null,
    var description: String? = null,
) {
//    fun toEntity() = Sanction(
//        id,
//        code,
//        description,
//        entity_id,
//    )
}