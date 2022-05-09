package com.notiprice.dto

import com.notiprice.entity.Product

/**
 * DTO товара.
 */
data class ProductDto(
    /**
     * Идентификатор товара.
     */
    var id: Long = 0,
    /**
     * Название товара.
     */
    var name: String,
    /**
     * Цена товара в виде десятичного числа.
     */
    var price: Double = 0.0,
    /**
     * Валюта.
     */
    var currency: String = "",
    /**
     * URL на товар.
     */
    var url: String,
    /**
     * Путь до цены на странице товара.
     */
    var xpath: String,
    /**
     * Значение по пути цены товара на странице.
     */
    var priceStr: String = ""
)

fun ProductDto.toEntity() = Product(id, name, price, currency, url, xpath, priceStr, 0L)