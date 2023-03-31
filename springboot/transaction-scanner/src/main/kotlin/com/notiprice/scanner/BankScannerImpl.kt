package com.notiprice.scanner

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction
import org.springframework.stereotype.Component

@Component
class BankScannerImpl : Scanner {
    companion object {
        const val TYPE = "BankSanctionList"
    }
    override fun sanctionIndicator(tr: Transaction, s: Sanction): Boolean {
        return s.type == TYPE && (s.value == tr.bankTo || s.value == tr.bankFrom)
    }
}