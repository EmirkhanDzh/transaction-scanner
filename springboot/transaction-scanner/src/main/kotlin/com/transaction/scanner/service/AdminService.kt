package com.transaction.scanner.service

import com.transaction.scanner.dao.AdminRepository
import com.transaction.scanner.entity.Admin
import org.springframework.stereotype.Component

@Component
class AdminService(private val adminRepository: AdminRepository): UserService<Admin>(adminRepository) {
    override fun encodePassword(user: Admin) {
        user.adminPassword = passwordEncoder.encode(user.adminPassword)
    }

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает пользователя, если нет, то бросает исключение.
     */
    override fun login(user: Admin): Admin {
        val userDb = adminRepository.findByAdminUsername(user.adminUsername)
        requireNotNull(userDb) {"Username is incorrect!"}

        if (!passwordEncoder.matches(user.adminPassword, userDb.adminPassword)) {

            throw IllegalArgumentException("Password is incorrect!")
        }

        return userDb
    }
}