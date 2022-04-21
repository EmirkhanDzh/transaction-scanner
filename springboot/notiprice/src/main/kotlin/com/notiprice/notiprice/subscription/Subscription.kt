package com.notiprice.notiprice.subscription

import java.time.LocalDate

data class Subscription(
    val userId: Long,
    val productId: Long,
    val lastCheck: LocalDate,
    val intervalInMinutes: Int,
)
