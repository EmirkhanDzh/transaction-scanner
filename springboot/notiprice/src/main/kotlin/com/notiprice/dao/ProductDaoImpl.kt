package com.notiprice.dao

import com.notiprice.entity.Product
import com.notiprice.entity.ProductRowMapper
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Types

@Primary
@Component
class ProductDaoImpl(private val jdbcTemplate: JdbcTemplate) : ProductDao {

    private val productRowMapper = ProductRowMapper()

    /**
     * Сохранение экземпляра класса Product в базе данных.
     */
    override fun save(product: Product): Product {
        val keyHolder: KeyHolder = GeneratedKeyHolder()

        val numOfUpdates = jdbcTemplate.update(
            { connection: Connection ->

                val sql = """
                INSERT INTO products 
                    (name, price, currency, url, xpath, price_str, last_check)
                        VALUES 
                    (?, ?, ?, ?, ?, ?, ?)
                        RETURNING id
            """.trimIndent()

                val ps = connection
                    .prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                    )

                ps.setString(1, product.name)
                ps.setDouble(2, product.price)
                ps.setString(3, product.currency)
                ps.setString(4, product.url)
                ps.setString(5, product.xpath)
                ps.setString(6, product.priceStr)
                ps.setLong(7, product.lastCheck)
                ps
            },
            keyHolder,
        )

        require(numOfUpdates == 1)

        product.id = keyHolder.key as Long

        return product
    }

    /**
     * Получение продукта по идентификатору.
     */
    override fun findByIdOrNull(id: Long): Product? {
        val sql = """
            SELECT * FROM products WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf<Any>(id),
            intArrayOf(Types.BIGINT),
            productRowMapper
        ).firstOrNull()
    }

    /**
     * Изменение данных о продукте.
     */
    override fun update(product: Product): Int {
        val sql = """
            UPDATE products 
                SET 
                    name = ?, price = ?, currency = ?, url = ?, xpath = ?, price_str = ?, last_check = ?
                WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            product.name,
            product.price,
            product.currency,
            product.url,
            product.xpath,
            product.priceStr,
            product.lastCheck,
            product.id
        )
    }

    /**
     * Удаление продукта.
     */
    override fun delete(productId: Long): Int {

        val sql = """
            DELETE FROM products WHERE id = ?
        """.trimIndent()


        return jdbcTemplate.update(
            sql,
            productId
        )
    }

    /**
     * Получение товаров пользователя по пользовательскому имени.
     */
    override fun findAllUserProducts(username: String): List<Product> {
        val sql = """
            SELECT * 
                FROM products p
                JOIN subscriptions s on p.id = s.product_id
                JOIN users u on s.chat_id = u.chat_id
                WHERE u.username = ?
        """.trimIndent()

        return jdbcTemplate.query(sql, arrayOf(username), intArrayOf(Types.VARCHAR), productRowMapper)
    }

    /**
     * Получение товаров для сканирования. Возвращает товары, которые не проверялись определенный
     * интервал времени в секундах timeInterval.
     */
    override fun findToCheck(recheckInSeconds: Int, limit: Int): List<Product> {
        val sql = """
            SELECT * FROM products 
                WHERE last_check + ? <= ?
            ORDER BY last_check LIMIT ?
        """.trimIndent()

        val now = System.currentTimeMillis()

        return jdbcTemplate.query(
            sql,
            arrayOf(recheckInSeconds * 1000, now, limit),
            intArrayOf(Types.BIGINT, Types.BIGINT, Types.BIGINT),
            productRowMapper
        )
    }

    /**
     * Получение xpath-ов по URL.
     */
    override fun findXpathByUrl(baseUrl: String): List<String> {
        val sql = """
            SELECT xpath, COUNT(id) AS cnt FROM products
                WHERE url LIKE ? 
                    GROUP BY xpath 
                    ORDER BY cnt desc
        """.trimIndent()

        return jdbcTemplate.query(
            sql,
            arrayOf("%$baseUrl%"),
            intArrayOf(Types.VARCHAR)
        ) { rs: ResultSet, _: Int ->
            rs.getString(Columns.xpath)
        }
    }
}