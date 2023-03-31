package com.notiprice.dto

import com.notiprice.entity.User
import com.notiprice.enums.Role

/**
 * DTO пользователя.
 */
data class UserDto(

    var id: Long,
    /**
     * Логин пользователя.
     */
    var username: String,
    /**
     * Пароль пользователя.
     */
    var password: String,

    var role: Role = Role.OPERATOR,
) {
    fun toEntity() = User(id, username, password, role)
}