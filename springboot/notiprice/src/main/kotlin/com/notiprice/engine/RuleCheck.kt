package com.notiprice.engine

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction

interface RuleCheck {
    /**
     * возвращает id санкции, к которой относится текущая транзакция
     */
    fun checkRule(transaction: Transaction, sanctionList: List<Sanction>): Sanction?
}