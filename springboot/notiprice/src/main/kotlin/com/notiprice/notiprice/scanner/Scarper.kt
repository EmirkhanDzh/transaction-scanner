package com.notiprice.scarper

import mu.KotlinLogging
import org.jsoup.Jsoup
import us.codecraft.xsoup.Xsoup


private val logger = KotlinLogging.logger {}

fun getValueByXpath(url: String, xpath: String): String? =
    try {
//        val apiKey = "cc4f9ce0-bcbc-11ec-94c9-1125c5e45be1"
//        val apiEndPoint = "https://app.zenscrape.com/api/v1/get" +
//                "?apikey=$apiKey" +
//                "&url=$url"

        Xsoup.compile(xpath)
            .evaluate(Jsoup.connect(url).get())
            .elements.first()
            ?.childNodes()?.first()
            ?.outerHtml()?.replace("&nbsp;", " ")
    } catch (th: Throwable) {
        logger.warn { "Cannot read a value by this xpath" }
        null
    }