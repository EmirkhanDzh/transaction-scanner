package com.notiprice.engine

import com.notiprice.dao.SanctionDao
import com.notiprice.dao.SanctionDaoImpl
import com.notiprice.entity.Paysystem
import com.notiprice.entity.Sanction
import com.notiprice.entity.Result
import com.notiprice.entity.Transaction
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class RulesEngine(
    val ruleCheckList: List<RuleCheck>,
    val sanctionDao: SanctionDaoImpl,
) {
    fun check(transaction: Transaction) {
        val transactionListFromDb = sanctionDao.getAll()
        val result = Result(
            transactionId = transaction.id
        )
        ruleCheckList.asSequence().forEach {
            val resultByRule = it.checkRule(transaction, transactionListFromDb)
            if ( resultByRule != null) {
                when(it){
                    is ClientRuleCheck -> result.apply { this.clientSanctionId = resultByRule.id}
                    is BankRuleCheck -> result.apply { this.bankSanctionId = resultByRule.id }
                    is PaysystemRuleCheck -> result.apply { this.paysystemSanctionId = resultByRule.id }
                    is CountriesRuleCheck -> result.apply { this.countrySanctionId = resultByRule.id }
                    else -> error("Nonidentified rule check type")
                }
            }
        }
    }
}