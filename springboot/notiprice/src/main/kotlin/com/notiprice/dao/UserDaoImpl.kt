package com.notiprice.dao

import com.notiprice.entity.Columns
import com.notiprice.entity.User
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Types

@Primary
@Component
class UserDaoImpl(private val jdbcTemplate: JdbcTemplate) : UserDao {
    /**
     * Добавление в базу данных экземпляра класса User.
     */
    override fun save(user: User): User {
        val sql = """
            INSERT INTO users 
                (chat_id, username, password)
                    values
                (?, ?, ?)
                
        """.trimIndent()

        val numOfUpdates = jdbcTemplate.update(
            sql,
            user.chatId, user.username, user.password
        )

        require (numOfUpdates == 1)

        return user
    }

    /**
     * Получение User по идентификатору.
     */
    override fun findByIdOrNull(id: Long): User? {

        val sql = """
            SELECT * FROM users WHERE chat_id = ?
            
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(id),
            intArrayOf(Types.BIGINT)
        ) { rs: ResultSet, _: Int ->
            User(
                rs.getLong(Columns.chatId),
                rs.getString(Columns.username),
                rs.getString(Columns.password)
            )
        }.firstOrNull()
    }

    /**
     * Получение User по пользовательскому имени.
     */
    override fun findByUsernameOrNull(name: String): User? {
        val sql = """
            SELECT * FROM users WHERE username = ?
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(name),
            intArrayOf(Types.VARCHAR)
        ) { rs: ResultSet, _: Int ->
            User(
                rs.getLong(Columns.chatId),
                rs.getString(Columns.username),
                rs.getString(Columns.password)
            )
        }.firstOrNull()
    }

    /**
     * Изменение данных User.
     */
    override fun update(user: User): Int {
        val sql = """
            UPDATE users  
                SET username = ?, password = ?
                    WHERE chat_id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            user.username, user.password, user.chatId,
        )
    }

    /**
     * Удаление User.
     */
    override fun delete(userId: Long): Int {

        return jdbcTemplate.update(
            "DELETE FROM users WHERE chat_id = ?",
            userId
        )
    }
}