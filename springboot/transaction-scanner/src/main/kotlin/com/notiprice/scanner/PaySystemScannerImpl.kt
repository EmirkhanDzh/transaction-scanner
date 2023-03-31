package com.notiprice.scanner

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction
import org.springframework.stereotype.Component

@Component
class PaySystemScannerImpl : Scanner {
    companion object {
        const val TYPE = "PaySystemSanctionList"
    }

    override fun sanctionIndicator(tr: Transaction, s: Sanction): Boolean {
        return s.type == TYPE && (s.value == tr.paySystemTo || s.value == tr.paySystemFrom)
    }


}