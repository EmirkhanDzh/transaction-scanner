package com.notiprice.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Сервис для работы с пользователем.
 */
abstract class UserService<T>(
    private val userDao: JpaRepository<T, Long>,
//                              private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    /**
     * Для хеширования пароля.
     */
    protected val passwordEncoder = BCryptPasswordEncoder()

    abstract fun encodePassword(user: T)

    /**
     * Добавление пользователя.
     */
    fun addUser(user: T): T {
        encodePassword(user)

        return try {
            userDao.save(user)
        } catch (th: Throwable) {

            throw IllegalArgumentException("Username already exist")
        }
    }

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает пользователя, если нет, то бросает исключение.
     */
    abstract fun login(user: T): T


}