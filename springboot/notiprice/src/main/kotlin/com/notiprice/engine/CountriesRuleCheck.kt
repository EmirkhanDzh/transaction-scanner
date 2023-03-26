package com.notiprice.engine

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(4)
class CountriesRuleCheck: RuleCheck {
    override fun checkRule(transaction: Transaction, sanctionList: List<Sanction>): Sanction? {
        TODO()
    }
}