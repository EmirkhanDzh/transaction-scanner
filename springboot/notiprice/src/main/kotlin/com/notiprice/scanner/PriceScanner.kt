package com.notiprice.scanner

import com.notiprice.dao.ProductDao
import com.notiprice.dao.SubscriptionDao
import com.notiprice.entity.Product
import kotlinx.coroutines.*
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

/**
 * Сканирует продукты, если цена меняется, то отправляет сообщение пользователю.
 */
@EnableScheduling
@Component
class PriceScanner(
    /**
     * DAO продукта для работы с базой данных.
     */
    private val productDao: ProductDao,
    /**
     * DAO для класса Subscription для работы с базой данных.
     */
    private val subscriptionDao: SubscriptionDao,
    /**
     * Клиент для HTTP запросов.
     */
    private val restTemplate: RestTemplate,
    /**
     * Интервал времени в секундах. Показывает интервал проверки продуктов.
     */
    @Value("\${scan.recheck.in.seconds}") private val recheckInSeconds: Int,
    /**
     * Сколько товаров обрабатывать одновременно.
     */
    @Value("\${process.product.limit}") private val limit: Int,
    @Value("\${bot.token}") private val botToken: String,
) {
    /**
     * Запускает сканирование с интервалом fixedDelayString в секундах.
     * Проверяет товары, которые не проверялись определенный интервал времени в секундах timeIntervalInSeconds,
     * если их цена меняется, то пользователям отправляются сообщения через Телеграм бот.
     */
    @Scheduled(fixedDelayString = "\${scan.fixedDelay.in.seconds}", timeUnit = TimeUnit.SECONDS)
    fun scan(

    ) = runBlocking {

        var products: List<Product>

        do {
            products = productDao.findToCheck(recheckInSeconds, limit)

            products.map {
                launch(Dispatchers.IO) {
                    val currentPrice = getValueByXpath(url = it.url, xpath = it.xpath)

                    if (currentPrice != null &&
                        currentPrice.isNotEmpty() &&
                        currentPrice.isNotBlank() &&
                        currentPrice != it.priceStr
                    ) {
                        sendNotifications(it, currentPrice)
                        it.priceStr = currentPrice
                    }

                    it.lastCheck = System.currentTimeMillis()
                    productDao.update(it)
                }
            }
                .joinAll()

        } while (products.isNotEmpty())

    }

    /**
     * Отправка сообщения пользователям, которые следят за товаром.
     */
    suspend fun sendNotifications(product: Product, currentPrice: String) {

        val chatIds = subscriptionDao.findChatIdsByProductId(product.id)


        for (chatId in chatIds) {

            val text = "Price of the product was changed" +
                    "\nname ${product.name}" +
                    "\nprice $currentPrice" +
                    "\nurl ${product.url}"
            // По хорошему нужно через WebClient
            val response: ResponseEntity<String> =
                withContext(Dispatchers.IO) {
                    restTemplate.getForEntity(
                        "https://api.telegram.org/bot$botToken/sendMessage?chat_id=$chatId&text=$text",
                        String::class.java
                    )
                }

            if (response.statusCode.is2xxSuccessful) {
                logger.info { "Notification of ${product.name} was sent to Telegram" }
            } else {
                logger.warn { "Cannot send notification of ${product.name}..." }
            }
        }

    }
}