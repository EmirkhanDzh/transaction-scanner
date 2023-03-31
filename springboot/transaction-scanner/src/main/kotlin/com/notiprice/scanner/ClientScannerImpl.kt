package com.notiprice.scanner

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction
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