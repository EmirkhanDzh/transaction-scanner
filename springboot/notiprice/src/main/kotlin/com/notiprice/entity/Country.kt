package com.notiprice.entity

data class Country(
    val id: Long = 0,
    val name: String? = null,
    val code: String? = null,
) {
    fun toDto() = Country(id, name, code)
}
