package com.notiprice.engine

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class TransactionScannerEngine(
    private val transactionScannerHandler: TransactionScannerHandler,
) {

    @Scheduled(fixedDelay = 3, timeUnit = TimeUnit.SECONDS)
    fun scan() {
        transactionScannerHandler.handle()
    }


}