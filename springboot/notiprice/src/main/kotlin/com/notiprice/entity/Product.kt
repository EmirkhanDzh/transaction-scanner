package com.notiprice.entity

import com.notiprice.dto.ProductDto
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

/**
 * Для отображения таблицы products из базы данных.
 */
data class Product(
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
    var currency: String,
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
    var priceStr: String = "",
    /**
     * Timestamp времени последней проверки цены товара.
     */
    var lastCheck: Long
)

fun Product.toDto() = ProductDto(id, name, price, currency, url, xpath, priceStr)

class ProductRowMapper : RowMapper<Product> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Product {

        return Product(
            rs.getLong(Columns.id),
            rs.getString(Columns.name),
            rs.getDouble(Columns.price),
            rs.getString(Columns.currency),
            rs.getString(Columns.url),
            rs.getString(Columns.xpath),
            rs.getString(Columns.priceStr),
            rs.getLong(Columns.lastCheck),
        )
    }

}
