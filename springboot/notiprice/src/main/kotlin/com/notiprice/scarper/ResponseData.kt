package com.notiprice.scarper

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseData(
    val error: String,
    val data: ProductData?
) {
    constructor() : this("", null)
}