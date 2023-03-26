package com.notiprice.service

import com.notiprice.dao.BankDao
import com.notiprice.dao.ClientDao
import com.notiprice.dao.CountryDao
import com.notiprice.dao.OperatorResultDao
import com.notiprice.dao.PaysystemDao
import com.notiprice.dao.ResultDaoImpl
import com.notiprice.dao.RulesEngineResultDao
import com.notiprice.dao.TransactionDao
import com.notiprice.dao.TransactionDaoImpl
import com.notiprice.dto.OperatorResultDto
import com.notiprice.dto.TransactionDto
import com.notiprice.entity.OperatorResult
import com.notiprice.entity.RulesEngineResult
import com.notiprice.entity.Transaction
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionDao: TransactionDao,
    // Создать классы-бины для интрефейсов
    val clientDao: ClientDao,
    val bankDao: BankDao,
    val countryDao: CountryDao,
    val paysystemDao: PaysystemDao,
    val operatorResultDao: OperatorResultDao,
    val rulesEngineResultDao: RulesEngineResultDao,
    val transactionScannerService: TransactionScannerService,
) {

    // подумать над тем как доставать данные из БД,
    // чтобы сконструировать TransactionDto, ведь из БД мы получаем только id'шники
    fun getAllTransactionByUserId(userId: Long) : List<TransactionDto> {
        val transactions = transactionDao.getAllByUserId(userId)
        return transactionEntityListToTransactionDtoList(transactions)
    }

    fun saveAndAnalyseTransactionList(transactionDtoList: List<TransactionDto>) {
        val transactionList = transactionDtoListToTransactionEntityList(transactionDtoList)
        saveTransactionList(transactionList)
    }

    // сохранять по батчам
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
        rulesEngineResultDao.findByIdOrNull(transactionId)

    fun getTransactionOperatorCheckingResult(transactionId: Long): OperatorResult? =
        operatorResultDao.findByIdOrNull(transactionId)

    fun createOperatorResult(operatorResultDto: OperatorResultDto) {
        // в базу сохраняем сущность
        TODO()
    }
}