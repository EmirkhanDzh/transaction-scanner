package com.notiprice.dao

import com.notiprice.entity.Transaction
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Types

@Component
class TransactionDaoImpl(
    private val jdbcTemplate: JdbcTemplate
) : TransactionDao {
    override fun save(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

    override fun findByIdOrNull(id: Long): Transaction? {
        val sql = """
            SELECT * 
            FROM transactions 
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(id),
            intArrayOf(Types.BIGINT),
        ) { rs: ResultSet, _: Int ->
            Transaction()
        }.firstOrNull()
    }

    override fun getAllByUserId(userId: Long): List<Transaction> {
        val sql = """
            SELECT * 
            FROM transactions 
            WHERE user_id = ?
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(userId),
            intArrayOf(Types.BIGINT),
        ) { rs: ResultSet, _: Int ->
            Transaction(
            )
        }
    }



    override fun update(transaction: Transaction): Int {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): Int {
        TODO("Not yet implemented")
    }


}