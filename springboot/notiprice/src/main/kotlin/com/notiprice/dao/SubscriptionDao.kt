package com.notiprice.dao

import com.notiprice.entity.Subscription

interface SubscriptionDao {
    /**
     * Добавление в базу данных экземпляра класса Subscription.
     */
    fun save(subscription: Subscription): Subscription

    /**
     * Получение Subscription по идентификатору.
     */
    fun findByIdOrNull(chatId: Long, productId: Long): Subscription?

    /**
     * Изменение данных Subscription.
     */
    fun update(product: Subscription)

    /**
     * Удаление Subscription.
     */
    fun delete(chatId: Long, productId: Long)

    /**
     * Получение идентификаторов чата по идентификатору продуктов.
     */
    fun findChatIdsByProductId(productId: Long): List<Long>
}