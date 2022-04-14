package com.notiprice.scarper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64


object ScarperBot {

    fun get(urlToScrape: String): String {
        val json = json(urlToScrape)
//        val json = jsonMock()
        val mapper = ObjectMapper()
        val data: ResponseData = mapper.readValue(json)
        return mapper.writeValueAsString(data.data)
    }

    fun jsonMock() = "{\n" +
            "    \"error\": null,\n" +
            "    \"data\": {\n" +
            "      \"title\": \"Apple iPhone XR 64GB Red Unlocked A2105 GSM SEALED BOX- 1 Year Apple Warranty\",\n" +
            "      \"description\": \"Apple iPhone XR. 1 YEAR APPLE CARE WARRANTY.\",\n" +
            "      \"image\": \"https://www.scraping-bot.io/iphone_example_ebay_files/s-l500.png\",\n" +
            "      \"price\": 689,\n" +
            "      \"shippingFees\": 18,\n" +
            "      \"currency\": \"GBP\",\n" +
            "      \"isInStock\": true,\n" +
            "      \"EAN13\": \"0190198770660\",\n" +
            "      \"ASIN\": null,\n" +
            "      \"ISBN\": null,\n" +
            "      \"color\": \"White\",\n" +
            "      \"brand\": \"Apple\",\n" +
            "      \"category\": {\n" +
            "        \"name\": \"Mobile & Smart Phones\",\n" +
            "        \"url\": \"https://www.ebay.co.uk/b/Mobile-Smart-Phones-/9355\"\n" +
            "      },\n" +
            "      \"categories\": [\n" +
            "        {\n" +
            "          \"name\": \"Mobile Phones & Communication\",\n" +
            "          \"url\": \"https://www.ebay.co.uk/b/Mobile-Phones-Communication-/15032\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Mobile & Smart Phones\",\n" +
            "          \"url\": \"https://www.ebay.co.uk/b/Mobile-Smart-Phones-/9355\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"siteURL\": \"https://www.ebay.co.uk/itm/Apple-iPhone-XR-64GB-Red-Unlocked-A2105-GSM-SEALED-BOX-1-Year-Apple-Warranty-/123902112947\",\n" +
            "      \"siteHtml\": null,\n" +
            "      \"productHasVariations\": null,\n" +
            "      \"error\": null,\n" +
            "      \"statusCode\": null,\n" +
            "      \"isFinished\": null,\n" +
            "      \"isDead\": null,\n" +
            "      \"htmlLength\": 128016\n" +
            "    }\n" +
            "  }"

    fun json(urlToScrape: String): String {
        try {
            val username = "kak312"
            val apiKey = "i3PdfU8aESMQwKXj28KAbRqeQ"
            val originalInput = "$username:$apiKey"
            val encodedString = "Basic " + Base64.getEncoder().encodeToString(originalInput.toByteArray())
            val apiEndPoint = "http://api.scraping-bot.io/scrape/retail"
            val url = URL(apiEndPoint)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.requestMethod = "POST"
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            con.setRequestProperty("Authorization", encodedString)
            val useChrome = "false" //set to "true" if you want to use headless chrome for javascript rendering
            val premiumProxy = "false" //set to "true" if you want to use premium proxies Unblock Amazon,Google,Rakuten
//            val urlToScrape = "https://www.scraping-bot.io/example-ebay.html"
            val proxyCountry: String? = null //allows you to choose a country proxy (example: proxyCountry:"FR")
            val waitForNetworkRequests = "false" //set to 'true' if you want to use 'networkidle2'
            val param = "{\"url\":\"" + urlToScrape + "\"," +
                    "\"options\":{" +
                    "\"useChrome\":" + useChrome + "," +
                    "\"premiumProxy\":" + premiumProxy + "," +
                    "\"proxyCountry\":" + proxyCountry + "," +
                    "\"waitForNetworkRequests\":" + waitForNetworkRequests +
                    "}" +
                    "}"
            con.doOutput = true
            val out: OutputStream = con.outputStream
            out.write(param.toByteArray())
            out.flush()
            out.close()
            val status: Int = con.responseCode
            println(status)
            val `in` = BufferedReader(
                InputStreamReader(con.inputStream)
            )
            var inputLine: String?
            val content = StringBuilder()
            while (`in`.readLine().also { inputLine = it } != null) {
                content.append(inputLine)
            }
            val jsonResponse = content.toString()
//            println(jsonResponse)
            `in`.close()
            con.disconnect()

            return jsonResponse
        } catch (e: Exception) {
            println("An error occurred while scraping:$e")
            return ""
        }
    }
}