package com.notiprice.notiprice.product

data class Product(
    var id: Long = 0,
    var name: String,
    var price: Double = 0.0,
    var currency: String,
    var url: String,
    var xpath: String,
    var priceStr: String = ""
)
