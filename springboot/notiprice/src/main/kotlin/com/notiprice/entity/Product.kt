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
    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call `next()` on
     * the ResultSet; it is only supposed to map values of the current row.
     * @param rs the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be `null`)
     * @throws SQLException if an SQLException is encountered getting
     * column values (that is, there's no need to catch SQLException)
     */
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
