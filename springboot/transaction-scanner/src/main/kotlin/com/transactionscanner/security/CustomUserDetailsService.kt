package com.transactionscanner.security

import com.transactionscanner.service.UserService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

/**
 * Сервис для класса CustomUserDetails.
 */
@Component
class CustomUserDetailsService(private val userService: UserService) : UserDetailsService {
    /**
     * Получение экземпляра класса CustomUserDetails по пользовательскому имени.
     */
    override fun loadUserByUsername(username: String?): CustomUserDetails {
        val user = userService.getUserByUsername(username!!)
        return user.toCustomUserDetails()
    }
}