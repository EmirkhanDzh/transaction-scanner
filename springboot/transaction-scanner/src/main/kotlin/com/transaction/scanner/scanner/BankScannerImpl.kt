package com.transaction.scanner.scanner

import com.transaction.scanner.entity.Sanction
import com.transaction.scanner.entity.Transaction
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