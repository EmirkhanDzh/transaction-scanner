package com.notiprice.notiprice.entity

import com.notiprice.notiprice.dto.UserDto

data class User(
    val chatId: Long,
    val username: String,
    val password: String,
)

fun User.toDto() = UserDto(chatId, username, password)