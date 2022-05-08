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
 * Scanning db to find products to check
 */
@EnableScheduling
@Component
class PriceScanner(
    private val productDao: ProductDao,
    private val subscriptionDao: SubscriptionDao,
    private val restTemplate: RestTemplate,
    @Value("\${scan.recheck.in.seconds}") private val timeIntervalInSeconds: Int,
    @Value("\${process.product.limit}") private val limit: Int
) {

    @Scheduled(fixedDelayString = "\${scan.fixedDelay.in.seconds}", timeUnit = TimeUnit.SECONDS)
    fun scan(

    ) = runBlocking {

        var products: List<Product>

        do {
            products = productDao.findToCheck(timeIntervalInSeconds, limit)

            products.map {
                launch(Dispatchers.IO) {
                    logger.info { it.toString() }
                    val currentPrice = getValueByXpath(url = it.url, xpath = it.xpath)

                    if (currentPrice == null || currentPrice == "") {
                        logger.info { "Cannot get price from the object" }
                        it.lastCheck = System.currentTimeMillis()
                        productDao.update(it)
                        return@launch
                    }

                    if (currentPrice == it.priceStr) {

                        logger.info { "Price wasn't changed: $currentPrice" }
                        it.lastCheck = System.currentTimeMillis()
                        productDao.update(it)
                        return@launch
                    }

                    sendNotifications(it, currentPrice)

                    it.lastCheck = System.currentTimeMillis()
                    it.priceStr = currentPrice
                    productDao.update(it)

                }
            }.joinAll()

            val i = 1

        } while (products.isNotEmpty())

    }

    fun sendNotifications(product: Product, currentPrice: String) {
        val chatIds = subscriptionDao.findChatIdsByProductId(product.id)

        for (chatId in chatIds) {

            val text = "Price of the product was changed" +
                    "\nname ${product.name}" +
                    "\nprice $currentPrice" +
                    "\nurl ${product.url}"

            val response: ResponseEntity<String> =
                restTemplate.getForEntity(
                    "https://api.telegram.org/bot5119272724:AAGaZ5I0olOEpDAZIqT-TXTJiJqBNxfpb_w/" +
                            "sendMessage?chat_id=$chatId&text=$text",
                    String::class.java
                )

            if (response.statusCode.is2xxSuccessful) {
                logger.info { "message was sent to telegram: new price: $currentPrice" }
            } else {
                logger.info { "Cannot send message: new price: $currentPrice to " }
            }
        }
    }
}