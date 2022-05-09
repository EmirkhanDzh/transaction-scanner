package com.notiprice.service

import com.notiprice.dao.UserDao
import com.notiprice.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * Сервис для работы с пользователем.
 */
@Service
class UserService(private val userDao: UserDao) {
    /**
     * Для хеширования пароля.
     */
    private val passwordEncoder = BCryptPasswordEncoder()

    /**
     * Добавление пользователя.
     */
    fun addUser(user: User): User {

        user.password = passwordEncoder.encode(user.password)

        return userDao.save(user)
    }

    /**
     * Получение пользователя по идентификатору.
     */
    fun getProductById(id: Long): User {
        return userDao.findByIdOrNull(id) ?: throw IllegalArgumentException("No such element")//ToDo: write a norm mess
    }

    /**
     * Получение пользователя по пользовательскому имени.
     */
    fun getUserByUsername(username: String): User {
        return userDao.findByUsernameOrNull(username) ?: throw IllegalArgumentException("No such element")//ToDo: write a norm mess
    }

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает пользователя, если нет, то бросает исключение.
     */
    fun login(user: User): User {

        val userDb = getUserByUsername(user.username)

        if(!passwordEncoder.matches(user.password, userDb.password)) {

            throw IllegalArgumentException("Password is incorrect")
        }

        return userDb
    }

    /**
     * Изменение данных о пользователе.
     */
    fun updateUser(user: User) {
        userDao.update(user) //ToDo: throw ex there
    }

    /**
     * Удаление пользователя.
     */
    fun deleteProduct(id: Long) {
        userDao.delete(id)
    }
}