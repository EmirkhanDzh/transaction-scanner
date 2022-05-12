package com.notiprice.service

import com.notiprice.entity.Product
import com.notiprice.dao.ProductDao
import com.notiprice.dao.SubscriptionDao
import com.notiprice.entity.Subscription
import com.notiprice.scanner.getValueByXpath
import org.jsoup.Jsoup
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import us.codecraft.xsoup.Xsoup
import java.net.URL

//private val logger = KotlinLogging.logger {}

/**
 * Сервис для товаров.
 */
@Service
class ProductService(
    private val productDao: ProductDao,
    private val subscriptionDao: SubscriptionDao,
    private val userService: UserService
) {
    /**
     * Добавление товара. Функция является транзакционной.
     */
    @Transactional
    fun addProduct(product: Product, username: String): Product {

        if (product.url.contains("?")) {
            product.url = product.url.split("?").first()
        }
        // TODO: вынести название в subscriptions, добавлять, если нет продуктов с таким же url и xpath
        product.lastCheck = System.currentTimeMillis()
        val savedProduct = productDao.save(product)

        // throws exception if it doesn't exist
        val user = userService.getUserByUsername(username)

        subscriptionDao.save(Subscription(user.chatId, savedProduct.id))

        return savedProduct
    }

    /**
     * Получение товара по идентификатору.
     */
    fun getProductById(id: Long): Product {
        return productDao.findByIdOrNull(id)
            ?: throw IllegalArgumentException("No product with id = $id")
    }

    /**
     * Получение продуктов пользователя.
     */
    fun getAllUserProducts(username: String): List<Product> {
        return productDao.findAllUserProducts(username)
    }

    /**
     * Изменение данных о товаре.
     */
    fun updateProduct(product: Product) {
        val prevProduct = getProductById(product.id)
        product.lastCheck = prevProduct.lastCheck
        product.priceStr = prevProduct.priceStr
        require(productDao.update(product) == 1)
    }

    /**
     * Удаление продукта.
     */
    fun deleteProduct(id: Long) {
        require(productDao.delete(id) == 1)
    }

    /**
     * Получает страницу по URL и выделяет элемент по xpath и возвращает страницу.
     */
    fun getHtmlWithHighlightedElement(url: String, xpath: String): String {
        try {
            val doc = Jsoup.connect(url).get()

            Xsoup.compile(xpath)
                .evaluate(doc)
                .elements.first()
                ?.attr("style", "background-color: #FFFF00")

            return doc.toString()
        } catch (ex: Exception) {

            //logger.warn { "Cannot get page $url" }
            throw IllegalArgumentException("Cannot get page $url")
        }

    }

    /**
     * Получение xpath продукта по URL. В базе данных ищутся xpath от базового домена URL.
     * Найденные xpath проверяются можно ли получить значение по этому xpath значение.
     * Если таких несколько, то выбирается самый популярный.
     */
    fun getProductXpathByUrl(urlString: String): String {

        require(urlString.isNotBlank() && urlString.isNotEmpty())
        val url = URL(urlString)
        val baseUrl = url.host
        val candidates = productDao.findXpathByUrl(baseUrl)

        for (xpath in candidates) {

            if (getValueByXpath(urlString, xpath) != null) {
                return xpath
            }
        }
        return ""
    }
}