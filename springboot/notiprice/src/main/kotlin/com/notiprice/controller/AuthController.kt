package com.notiprice.controller

import com.notiprice.config.jwt.JwtProvider
import com.notiprice.dto.UserDto
import com.notiprice.dto.toEntity
import com.notiprice.entity.toDto
import com.notiprice.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
//@CrossOrigin(origins = ["*"])
//@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/auth")
class AuthController(private val userService: UserService, private val jwtProvider: JwtProvider) {

    @PostMapping("sign-up")
    fun addUser(@RequestBody user: UserDto): UserDto {
        val savedUser = userService.addUser(user.toEntity()).toDto()
        savedUser.password = ""
        return savedUser
    }

    @PostMapping("/sign-in")
    fun login(@RequestBody user: UserDto): String {
        val savedUser = userService.login(user.toEntity()).toDto()

        return jwtProvider.generateToken(savedUser.username)
    }
}