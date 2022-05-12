package com.notiprice.dao

import com.notiprice.entity.Columns
import com.notiprice.entity.Subscription
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Types

@Primary
@Component
class SubscriptionDaoImpl(private val jdbcTemplate: JdbcTemplate) : SubscriptionDao {
    /**
     * Добавление в базу данных экземпляра класса Subscription.
     */
    override fun save(subscription: Subscription): Subscription {

        val sql = """
            INSERT INTO subscriptions
                (chat_id, product_id)
                    VALUES 
                (?, ?)
        """.trimIndent()

        val numOfUpdates = jdbcTemplate.update(
            sql,
            subscription.chatId, subscription.productId
        )

        require(numOfUpdates == 1)

        return subscription
    }

    /**
     * Получение Subscription по идентификатору.
     */
    override fun findByIdOrNull(chatId: Long, productId: Long): Subscription? {
        val sql = """
            SELECT * FROM subscriptions WHERE chat_id = ? AND product_id = ? LIMIT 1
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(chatId, productId),
            intArrayOf(Types.BIGINT, Types.BIGINT)
        ) { rs: ResultSet, _: Int ->
            Subscription(
                rs.getLong(Columns.chatId),
                rs.getLong(Columns.productId)
            )
        }.firstOrNull()
    }

    /**
     * Изменение данных Subscription.
     */
    override fun update(product: Subscription) {


        // TODO: Сейчас немного смысла не имеет, но исправим, когда добавим еще колонки в subscriptions
        val sql = """
            UPDATE subscriptions SET 
                chat_id = ?, product_id = ?
                    WHERE chat_id = ? AND product_id = ?
        """.trimIndent()

        val numOfUpdates = jdbcTemplate.update(
            sql,
            product.chatId, product.productId
        )
        // TODO: Проверять в сервисе, когда он будет
        require(numOfUpdates == 1)
    }

    /**
     * Удаление Subscription.
     */

    override fun delete(chatId: Long, productId: Long) {

        val sql = """
           DELETE FROM subscriptions WHERE chat_id = ? and product_id = ?
        """.trimIndent()

        val numOfUpdates = jdbcTemplate.update(
            sql,
            chatId, productId
        )
        // TODO: Проверять в сервисе, когда он будет
        require(numOfUpdates == 1)
    }

    /**
     * Получение идентификаторов чата по идентификатору продуктов.
     */
    override fun findChatIdsByProductId(productId: Long): List<Long> {

        val sql = """
            SELECT DISTINCT chat_id FROM subscriptions WHERE product_id = ?
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(productId),
            intArrayOf(Types.BIGINT)
        ) { rs: ResultSet, _: Int ->
            rs.getLong(Columns.chatId)
        }
    }
}