package com.notiprice.dto

import com.notiprice.entity.Product

data class ProductDto(
    var id: Long = 0,
    var name: String,
    var price: Double = 0.0,
    var currency: String = "",
    var url: String,
    var xpath: String,
    var priceStr: String = ""
)

fun ProductDto.toEntity() = Product(id, name, price, currency, url, xpath, priceStr, 0L)