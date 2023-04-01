package com.transaction.scanner.scanner

import com.transaction.scanner.entity.Sanction
import com.transaction.scanner.entity.Transaction
import org.springframework.stereotype.Component

@Component
class ClientScannerImpl : Scanner {
    companion object {
        const val TYPE = "ClientSanctionList"
    }

    override fun sanctionIndicator(tr: Transaction, s: Sanction): Boolean {
        return s.type == TYPE && (s.value == tr.clientTo || s.value == tr.clientFrom)
    }
}