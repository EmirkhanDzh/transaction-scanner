package com.transaction.scanner.scanner

import com.transaction.scanner.entity.Sanction
import com.transaction.scanner.entity.Transaction
import org.springframework.stereotype.Component

@Component
class CountryScannerImpl : Scanner {
    companion object {
        const val TYPE = "CountrySanctionList"
    }

    override fun sanctionIndicator(tr: Transaction, s: Sanction): Boolean {
        return s.type == TYPE && (s.value == tr.countryTo || s.value == tr.countryFrom)
    }

}