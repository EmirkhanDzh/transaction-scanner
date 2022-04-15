package com.notiprice.scarper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import us.codecraft.xsoup.Xsoup

class Scarper {

    fun getData(urlToScrape: String, xpath: String): String {
        val apiKey = "cc4f9ce0-bcbc-11ec-94c9-1125c5e45be1"
        val apiEndPoint = "https://app.zenscrape.com/api/v1/get" +
                "?apikey=$apiKey" +
                "&url=$urlToScrape"

        val doc: Document = Jsoup.connect(apiEndPoint).get()
        val result = Xsoup.compile(xpath).evaluate(doc).get()
        return ""
    }
}