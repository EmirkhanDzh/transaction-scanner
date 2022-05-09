package com.notiprice.entity

/**
 * Для отображения таблицы subscriptions из базы данных.
 */
data class Subscription(
    /**
     * Внешний ключ таблицы users.
     */
    val chatId: Long,
    /**
     * Внешний ключ таблицы products.
     */
    val productId: Long
)
