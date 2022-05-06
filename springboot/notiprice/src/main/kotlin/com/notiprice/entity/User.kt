package com.notiprice.entity

import com.notiprice.dto.UserDto

data class User(
    val chatId: Long,
    val username: String,
    val password: String,
)

fun User.toDto() = UserDto(chatId, username, password)