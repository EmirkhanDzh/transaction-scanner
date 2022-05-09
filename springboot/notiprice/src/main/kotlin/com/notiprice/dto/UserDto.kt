package com.notiprice.dto

import com.notiprice.entity.User

/**
 * DTO пользователя.
 */
data class UserDto(
    /**
     * Идентификатор чата пользователя в Телеграме. Первичный ключ.
     */
    val chatId: Long,
    /**
     * Логин пользователя.
     */
    val username: String,
    /**
     * Пароль пользователя.
     */
    var password: String,
)

fun UserDto.toEntity() = User(chatId, username, password)