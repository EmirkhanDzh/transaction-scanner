package com.notiprice.dao

import com.notiprice.entity.Subscription
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.Types

/**
 * DAO для класса Subscription для работы с базой данных.
 */
@Component
class SubscriptionDao(private val jdbcTemplate: JdbcTemplate) {
    /**
     * Добавление в базу данных экземпляра класса Subscription.
     */
    fun save(subscription: Subscription): Subscription {

        val numOfUpdates = jdbcTemplate.update(
            "insert into $subscriptions ($chatId, $productId) values (?, ?)",
            subscription.chatId, subscription.productId
        )

        require(numOfUpdates == 1)

        return subscription
    }

    /**
     * Получение Subscription по идентификатору.
     */
    fun findByIdOrNull(chatId: Long, productId: Long): Subscription? {

        return jdbcTemplate.query(
            "select * from $subscriptions where $chatId = ? and $productId = ?",
            arrayOf<Any>(chatId, productId),
            intArrayOf(Types.BIGINT, Types.BIGINT)
        ) { rs: ResultSet, _: Int ->
            Subscription(
                rs.getLong(Companion.chatId),
                rs.getLong(Companion.productId)
            )
        }.firstOrNull()
    }

    /**
     * Изменение данных Subscription.
     */
    fun update(product: Subscription) {

        val numOfUpdates = jdbcTemplate.update(
            "update $subscriptions " +
                    "set $chatId = ?, " +
                    "$productId = ? " +
                    "where $chatId = ? and $productId = ?",
            product.chatId, product.productId
        )

        require(numOfUpdates == 1)
    }

    /**
     * Удаление Subscription.
     */
    fun delete(chatId: Long, productId: Long) {

        val numOfUpdates = jdbcTemplate.update(
            "delete $subscriptions where ${Companion.chatId} = ? and ${Companion.productId} = ?",
            chatId, productId
        )

        require(numOfUpdates == 1)
    }

    /**
     * Получение идентификаторов чата по идентификатору продуктов.
     */
    fun findChatIdsByProductId(productId: Long): List<Long> {

        return jdbcTemplate.query(
            "select distinct $chatId from $subscriptions where $productId = ?",
            arrayOf<Any>(productId),
            intArrayOf(Types.BIGINT)
        ) { rs: ResultSet, _: Int ->
            rs.getLong(chatId)
        }
    }

    companion object {
        const val subscriptions = "subscriptions"
        const val chatId = "chat_id"
        const val productId = "product_id"
    }
}