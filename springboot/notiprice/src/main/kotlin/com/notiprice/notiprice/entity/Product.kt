package com.notiprice.notiprice.entity

import com.notiprice.notiprice.dto.ProductDto

data class Product(
    var id: Long = 0,
    var name: String,
    var price: Double = 0.0,
    var currency: String,
    var url: String,
    var xpath: String,
    var priceStr: String = "",
    var lastCheck: Long
)

fun Product.toDto() = ProductDto(id, name, price, currency, url, xpath, priceStr)
