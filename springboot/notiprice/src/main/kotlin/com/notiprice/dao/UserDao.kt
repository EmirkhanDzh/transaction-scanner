package com.notiprice.dao

import com.notiprice.entity.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Types

/**
 * DAO для класса User для работы с базой дынных.
 */
@Component
class UserDao(private val jdbcTemplate: JdbcTemplate) {
    /**
     * Добавление в базу данных экземпляра класса User.
     */
    fun save(user: User): User {

        val numOfUpdates = jdbcTemplate.update(
            "insert into users ($chatId, $username, $password) values (?, ?, ?)",
            user.chatId, user.username, user.password
        )
        require(numOfUpdates == 1)

        return user
    }

    /**
     * Получение User по идентификатору.
     */
    fun findByIdOrNull(id: Long): User? {

        return jdbcTemplate.query(
            "select * from $users where $chatId = ?",
            arrayOf<Any>(id),
            intArrayOf(Types.BIGINT)
        ) { rs: ResultSet, _: Int ->
            User(
                rs.getLong(chatId),
                rs.getString(username),
                rs.getString(password)
            )
        }.firstOrNull()
    }

    /**
     * Получение User по пользовательскому имени.
     */
    fun findByUsernameOrNull(name: String): User? {

        return jdbcTemplate.query(
            "select * from $users where $username = ?",
            arrayOf<Any>(name),
            intArrayOf(Types.VARCHAR)
        ) { rs: ResultSet, _: Int ->
            User(
                rs.getLong(chatId),
                rs.getString(username),
                rs.getString(password)
            )
        }.firstOrNull()
    }
    /**
     * Изменение данных User.
     */
    fun update(user: User) {

        val numOfUpdates = jdbcTemplate.update(
            "update $users " +
                    "set $chatId = ?, " +
                    "$username = ?, " +
                    "$password = ?",
            user.chatId, user.username, user.password
        )

        require(numOfUpdates == 1)
    }

    /**
     * Удаление User.
     */
    fun delete(userId: Long) {

        val numOfUpdates = jdbcTemplate.update(
            "delete $users where id = ?",
            userId
        )

        require(numOfUpdates == 1)
    }

    companion object {
        const val users = "users"
        const val chatId = "chat_id"
        const val username = "username"
        const val password = "password"
    }
}