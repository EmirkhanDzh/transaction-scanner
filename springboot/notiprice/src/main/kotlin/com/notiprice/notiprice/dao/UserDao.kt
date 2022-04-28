package com.notiprice.notiprice.dao

import com.notiprice.notiprice.entity.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Types

@Component
class UserDao(private val jdbcTemplate: JdbcTemplate) {


    fun save(user: User): User {
        val keyHolder: KeyHolder = GeneratedKeyHolder()

        val numOfUpdates = jdbcTemplate.update(
            "insert into users ($chatId, $username, $password) values (?, ?, ?)",
            user.chatId, user.username, user.password
        )
        require(numOfUpdates == 1)

        return user
    }

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

    fun delete(userId: Long) {

        val numOfUpdates = jdbcTemplate.update(
            "delete $users where id = ?",
            userId
        )

        require(numOfUpdates == 1)
    }


    @Deprecated("Only for developing")
    fun findAll(): List<User> = jdbcTemplate.query(
        "select * from $users"
    ) { rs: ResultSet, _: Int ->
        User(
            rs.getLong(chatId),
            rs.getString(username),
            rs.getString(password)
        )
    }

    companion object {
        const val users = "users"
        const val chatId = "chat_id"
        const val username = "username"
        const val password = "password"
    }
}