package com.notiprice.config.jwt

import com.notiprice.service.UserService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String?): CustomUserDetails {
        val user = userService.getUserByUsername(username!!)
        return user.toCustomUserDetails()
    }
}