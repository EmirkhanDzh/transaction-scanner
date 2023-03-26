package com.notiprice.service

import com.notiprice.dao.SanctionDao
import com.notiprice.dao.SanctionDaoImpl
import com.notiprice.dao.TransactionDaoImpl
import com.notiprice.engine.RulesEngine
import com.notiprice.entity.Transaction
import org.springframework.stereotype.Service

@Service
class TransactionScannerService(
    private val sanctionDao: SanctionDaoImpl,
    private val rulesEngine: RulesEngine,
) {
    fun analyseTransactionList(transactionListForCheck: List<Transaction>) {
        transactionListForCheck.asSequence().forEach {
            analyseTransaction(it)
        }
    }

    fun analyseTransaction(transactionForCheck: Transaction) {
        rulesEngine.check(transactionForCheck)
    }

}