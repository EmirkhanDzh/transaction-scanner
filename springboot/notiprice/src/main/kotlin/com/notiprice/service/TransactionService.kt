package com.notiprice.service

import com.notiprice.dao.ResultDaoImpl
import com.notiprice.dao.TransactionDaoImpl
import com.notiprice.dto.TransactionDto
import com.notiprice.entity.OperatorResult
import com.notiprice.entity.RulesEngineResult
import com.notiprice.entity.Transaction
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionDao: TransactionDaoImpl,
    val resultDao: ResultDaoImpl,
    val transactionScannerService: TransactionScannerService,
) {

    fun getAllTransactionByUserId(userId: Long) = transactionEntityListToTransactionDtoList(
        transactionDao.getAllByUserId(userId)
    )

    fun saveAndAnalyseTransactionList(transactionDtoList: List<TransactionDto>) {
        val transactionList = transactionDtoListToTransactionEntityList(transactionDtoList)
        saveTransactionList(transactionList)
        analyseTransactionList(transactionList)
        updateTransactionList(transactionList)
    }

    fun saveTransactionList(transactionList: List<Transaction>) {
        transactionList.asSequence().forEach {
            try {
                transactionDao.save(it)
            } catch(dae: DataAccessException) {
                System.err.println("error while saving transaction: ${it.id}")
            }
        }
    }

    fun analyseTransactionList(transactionList: List<Transaction>) {
        transactionScannerService.analyseTransactionList(transactionList)
    }

    fun updateTransactionList(transactionList: List<Transaction>) = transactionList.asSequence().forEach {
        transactionDao.update(it)
    }

    private fun transactionDtoListToTransactionEntityList(transactionDtoList: List<TransactionDto>) =
        transactionDtoList.asSequence().map {
            it.toEntity()
        }.toList()

    private fun transactionEntityListToTransactionDtoList(transactionEntityList: List<Transaction>) =
        transactionEntityList.asSequence().map {
            it.toDto()
        }.toList()

    fun getTransaction(transactionId: Long) = transactionDao.findByIdOrNull(transactionId)

    fun getTransactionRulesCheckingResult(transactionId: Long): RulesEngineResult? =
        resultDao.getRulesEngineCheckingResult(transactionId)

    fun getTransactionOperatorCheckingResult(transactionId: Long): OperatorResult? =
        resultDao.getOperatorCheckingResult(transactionId)
}