package com.notiprice.scarper

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductData(
    var title: String,
    var price: Double,
    var currency: String,
    var isInStock: Boolean,
    var image: String
)
{
    constructor() : this("", 0.0, "", false, "")
}