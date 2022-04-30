package com.notiprice.notiprice.scan

import com.notiprice.notiprice.dao.ProductDao
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
    private val restTemplate: RestTemplate
) {

//    val products = emptyList<Product>()
//        listOf(
//        Product(
//            1,
//            "Рубашка H&M",
//            0.0,
//            "RUB",
//            "https://www.avito.ru/odintsovo/odezhda_obuv_aksessuary/rubashka_hm_2366591359",
//            "//*[@id=\"price-value\"]/span/span/span[1]",
//            ""
//        )
//    )

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

                val response: ResponseEntity<String> = restTemplate.getForEntity(
                    "https://api.telegram.org/bot5119272724:AAGaZ5I0olOEpDAZIqT-TXTJiJqBNxfpb_w/sendMessage?chat_id=992338299&text=$currentPrice",
                    String::class.java
                )

                if (response.statusCode.is2xxSuccessful) {
                    it.lastCheck = System.currentTimeMillis()
                    it.priceStr = currentPrice
                    productDao.update(it)

                    logger.info { "message was sent to telegram: new price: $currentPrice" }

                } else {
                    logger.info { "Cannot send message: new price: $currentPrice to " }
                }
            }
        }.joinAll()
    }
}