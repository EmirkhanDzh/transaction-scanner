package com.notiprice.dto

import com.notiprice.enums.Role
import com.notiprice.entity.User

/**
 * DTO пользователя.
 */
data class UserDto(

    val id: Long,
    /**
     * Логин пользователя.
     */
    val username: String,
    /**
     * Пароль пользователя.
     */
    var password: String,

    var role: Role = Role.OPERATOR,
) {
    fun toEntity() = User(id, username, password, role)
}