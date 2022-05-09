package com.notiprice.entity

import com.notiprice.dto.UserDto

/**
 * Для отображения таблицы users из базы данных.
 */
data class User(
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

fun User.toDto() = UserDto(chatId, username, password)