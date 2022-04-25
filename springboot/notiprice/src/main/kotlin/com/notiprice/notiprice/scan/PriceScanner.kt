package com.notiprice.notiprice.scan

import com.notiprice.notiprice.product.Product
import com.notiprice.notiprice.product.ProductDao
import com.notiprice.scarper.getValueByXpath
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
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
    private val restTemplate: RestTemplate,
//    @Value("\${process.product.limit}") val processProductLimit: Int,
) {

    val products = listOf(
        Product(
            1,
            "PUMA x Helly Hansen RS-Connect",
            0.0,
            "RUB",
            "https://vk.com/club199905316?w=wall-199905316_2",
            "//*[@id=\"wpt-199905316_2\"]/div",
            ""
        )
    )

    /**
     * Scanning db to find products to check
     */
    @Scheduled(fixedDelayString = "\${scan.fixedDelay.in.seconds}", timeUnit = TimeUnit.SECONDS)
    fun scan() = runBlocking {

        logger.info { "Staring scanning" }




        products.map {
            launch(Dispatchers.IO) {

                val currentPrice = getValueByXpath(url = it.url, xpath = it.xpath)
//                val currentPrice = null
                if (currentPrice == null || currentPrice == it.priceStr || currentPrice == "") {
                    logger.info { "Cannot get object or price wasn't changed: \n current price = $currentPrice" }
                    return@launch
                }

                val response: ResponseEntity<String> = restTemplate.getForEntity(
                    "https://api.telegram.org/bot5119272724:AAGaZ5I0olOEpDAZIqT-TXTJiJqBNxfpb_w/sendMessage?chat_id=992338299&text=$currentPrice",
                    String::class.java
                )

                if(response.statusCode.is2xxSuccessful) {

                    it.priceStr = currentPrice
                    logger.info { "message was sent to telegram: new price: $currentPrice" }

                } else {
                    logger.info { "Cannot send message: new price: $currentPrice to " }
                }


//                mutex.withLock {
//                    logger.info { "Trying to send message: new price: $currentPrice" }
//                }
            }
        }.joinAll()

        // select user_id, product_id as time from subscription where last_check + interval_in_seconds >= now()
        // soring by last_check limit processProductLimit

        // foreach products scarp price and if prices doesn't match notify the user and update last_check
    }

//    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
//    fun test() = runBlocking {
//        logger.info { "Staring testing!!!" }
//    }


}