package com.notiprice.service

import com.notiprice.dao.OperatorRepository
import com.notiprice.dao.OperatorsResultRepository
import com.notiprice.dao.TransactionRepository
import com.notiprice.dto.CompleteOperatorResultRequestDto
import com.notiprice.dto.TransactionDto
import com.notiprice.entity.OperatorsResult
import com.notiprice.extension.toDto
import com.notiprice.extension.toEntity
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val operatorsResultRepository: OperatorsResultRepository,
    private val operatorRepository: OperatorRepository,
) {
    fun getTransactions(operatorId: Long, isChecked: Boolean): List<TransactionDto> =
        transactionRepository
            .findAllByOperatorResult_OperatorId(operatorId)
            .filter {
                if (isChecked) {
                    it.operatorResult?.rulesEngineResult != null && checkNotNull(it.operatorResult).isClear != null
                } else {
                    it.operatorResult?.rulesEngineResult != null && checkNotNull(it.operatorResult).isClear == null
                }
            }
            .map { it.toDto() }

    fun getTransaction(transactionId: Long): TransactionDto? =
        transactionRepository
            .findById(transactionId)
            .orElse(null)
            ?.toDto()

    fun completeOperatorResult(dto: CompleteOperatorResultRequestDto) {
        val result = operatorsResultRepository.findById(requireNotNull(dto.operatorResultId)).orElse(null)
        requireNotNull(result) { "There is no operatorResult with id = ${dto.operatorResultId}" }

        result.comment = dto.comment
        result.isClear = dto.isClear

        operatorsResultRepository.save(result)
    }

    fun saveTransactions(operatorId: Long, transactionList: List<TransactionDto>) {
        val transactions = transactionList.map { it.toEntity() }

        transactions.forEach {
            check(it.operatorResult == null) { "Operator result shouldn't have been set" }

            it.operatorResult = OperatorsResult().apply {
                operator = operatorRepository.findById(operatorId).get()
            }
        }

        transactionRepository.saveAll(transactions)
    }
}