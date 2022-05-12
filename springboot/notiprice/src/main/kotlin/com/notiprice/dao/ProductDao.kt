package com.notiprice.dao

import com.notiprice.entity.Product

interface ProductDao {
    /**
     * Сохранение экземпляра класса Product в базе данных.
     */
    fun save(product: Product): Product

    /**
     * Получение продукта по идентификатору.
     */
    fun findByIdOrNull(id: Long): Product?

    /**
     * Изменение данных о продукте.
     */
    fun update(product: Product): Int

    /**
     * Удаление продукта.
     */
    fun delete(productId: Long): Int

    /**
     * Получение товаров пользователя по пользовательскому имени.
     */
    fun findAllUserProducts(username: String): List<Product>

    /**
     * Получение товаров для сканирования. Возвращает товары, которые не проверялись определенный
     * интервал времени в секундах timeInterval.
     */
    fun findToCheck(timeIntervalInSeconds: Int, limit: Int): List<Product>

    /**
     * Получение xpath-ов по URL.
     */
    fun findXpathByUrl(baseUrl: String): List<String>
}