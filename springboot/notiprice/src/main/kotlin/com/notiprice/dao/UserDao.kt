package com.notiprice.dao

import com.notiprice.entity.User

interface UserDao {
    /**
     * Добавление в базу данных экземпляра класса User.
     */
    fun save(user: User): User

    /**
     * Получение User по идентификатору.
     */
    fun findByIdOrNull(id: Long): User?

    /**
     * Получение User по пользовательскому имени.
     */
    fun findByUsernameOrNull(name: String): User?

    /**
     * Изменение данных User.
     */
    fun update(user: User): Int

    /**
     * Удаление User.
     */
    fun delete(userId: Long): Int
}