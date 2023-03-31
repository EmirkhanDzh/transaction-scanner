package com.notiprice.engine

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TransactionScannerEngine(
    private val transactionScannerHandler: TransactionScannerHandler,
) {

    @Scheduled(cron = "0 * * * * *")
    fun scan() {
        transactionScannerHandler.handle()
    }


}