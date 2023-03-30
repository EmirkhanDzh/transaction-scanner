package com.notiprice.service

import com.notiprice.dao.UserDao
import com.notiprice.dao.UserDaoImpl
import com.notiprice.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * Сервис для работы с пользователем.
 */
@Service
class UserService(private val userDao: UserDao = UserDaoImpl()) {
    /**
     * Для хеширования пароля.
     */
    private val passwordEncoder = BCryptPasswordEncoder()

    /**
     * Добавление пользователя.
     */
    fun addUser(user: User): User {

        user.password = passwordEncoder.encode(user.password)

        return try {
            userDao.save(user)
        } catch (th: Throwable) {
            throw IllegalArgumentException("Username or Telegram already exist")
        }
    }

    /**
     * Получение пользователя по идентификатору.
     */
    fun getProductById(id: Long): User {
        return userDao.findByIdOrNull(id)
            ?: throw IllegalArgumentException("No such user with id = $id")
    }

    /**
     * Получение пользователя по пользовательскому имени.
     */
    fun getUserByUsername(username: String): User {
        return userDao.findByUsernameOrNull(username)
            ?: throw IllegalArgumentException("No such user with id = $username")
    }

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает пользователя, если нет, то бросает исключение.
     */
    fun login(user: User): User {

        val userDb = getUserByUsername(user.username)

        if (!passwordEncoder.matches(user.password, userDb.password)) {

            throw IllegalArgumentException("Password is incorrect!")
        }

        return userDb
    }

    /**
     * Изменение данных о пользователе.
     */
    fun updateUser(user: User) {
        require(userDao.update(user) == 1)
    }

    /**
     * Удаление пользователя.
     */
    fun deleteProduct(id: Long) {
        require(userDao.delete(id) == 1)
    }
}