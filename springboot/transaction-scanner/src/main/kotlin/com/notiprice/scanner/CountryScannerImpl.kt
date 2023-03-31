package com.notiprice.scanner

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction
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