package com.notiprice.dto

import com.notiprice.entity.User

class UserDto(
    val chatId: Long,
    val username: String,
    var password: String,
)

fun UserDto.toEntity() = User(chatId, username, password)