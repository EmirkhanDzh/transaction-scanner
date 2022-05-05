package com.notiprice.notiprice.scanner

import com.notiprice.notiprice.dao.ProductDao
import com.notiprice.notiprice.dao.SubscriptionDao
import com.notiprice.scarper.getValueByXpath
import kotlinx.coroutines.*
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

@EnableScheduling
@Component
class PriceScanner(
    private val productDao: ProductDao,
    private val subscriptionDao: SubscriptionDao,
    private val restTemplate: RestTemplate
) {

    /**
     * Scanning db to find products to check
     */
    @Scheduled(fixedDelayString = "\${scan.fixedDelay.in.seconds}", timeUnit = TimeUnit.SECONDS)
    fun scan() = runBlocking {

        logger.info { "Staring scanning" }

        val products = productDao.findToCheck()

        products.map {
            launch(Dispatchers.IO) {
                logger.info { it.toString() }
                val currentPrice = getValueByXpath(url = it.url, xpath = it.xpath)

                if (currentPrice == null || currentPrice == "") {
                    logger.info { "Cannot get price from the object" }
                    return@launch
                }

                if (currentPrice == it.priceStr) {

                    logger.info { "Price wasn't changed: $currentPrice" }
                    it.lastCheck = System.currentTimeMillis()
                    productDao.update(it)
                    return@launch
                }

                val chatIds = subscriptionDao.findChatIdsByProductId(it.id)

                for (chatId in chatIds) {

                    val response: ResponseEntity<String> = restTemplate.getForEntity(
                        "https://api.telegram.org/bot5119272724:AAGaZ5I0olOEpDAZIqT-TXTJiJqBNxfpb_w/" +
                                "sendMessage?chat_id=$chatId&text=$currentPrice",
                        String::class.java
                    )

                    if (response.statusCode.is2xxSuccessful) {
                        logger.info { "message was sent to telegram: new price: $currentPrice" }
                    } else {
                        logger.info { "Cannot send message: new price: $currentPrice to " }
                    }
                }

                it.lastCheck = System.currentTimeMillis()
                it.priceStr = currentPrice
                productDao.update(it)

            }
        }.joinAll()
    }
}