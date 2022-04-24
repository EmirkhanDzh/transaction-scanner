package com.notiprice.notiprice.product

data class Product(
    var id: Long,
    var name: String,
    var price: Double,
    var currency: String,
    var url: String,
    var xpath: String
)
