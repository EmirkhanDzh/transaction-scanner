package com.notiprice.engine

import com.notiprice.dao.SanctionRepository
import com.notiprice.dao.TransactionDao
import com.notiprice.dao.TransactionRepository
import com.notiprice.entity.RulesEngineResult
import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction
import com.notiprice.scanner.Scanner
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class TransactionScannerHandler(
    private val transactionDao: TransactionDao,
    private val transactionRepository: TransactionRepository,
    private val sanctionRepository: SanctionRepository,
    private val scanners: List<Scanner>
) {

    @Transactional
    fun handle() {
        val transactionIds = transactionDao.getTransactionIdsToScan()

        val transactions = transactionRepository.findAllById(transactionIds)

        val sanctions = sanctionRepository.findAll()

        transactions.map {
            handleTransaction(it, sanctions)
        }

        transactionRepository.saveAll(transactions)
    }

    private fun handleTransaction(transaction: Transaction, sanctions: List<Sanction>) {
        val detectedSanctions = mutableSetOf<Sanction>()

        scanners.map {
            detectedSanctions.addAll(it.scan(transaction, sanctions))
        }
        val result = RulesEngineResult()

        result.isClear = detectedSanctions.isEmpty()

        if (detectedSanctions.isNotEmpty()) {
            result.sanction = detectedSanctions.first()
        }

        checkNotNull(transaction.operatorResult).rulesEngineResult = result
    }
}