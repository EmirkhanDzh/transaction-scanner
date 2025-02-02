package com.transaction.scanner.scanner

import com.transaction.scanner.entity.Sanction
import com.transaction.scanner.entity.Transaction

interface Scanner {
    fun scan(tr: Transaction, sanctions: List<Sanction>): List<Sanction> {

        return sanctions.filter { s ->
            sanctionIndicator(tr, s)
        }
    }

    fun sanctionIndicator(tr: Transaction, s: Sanction): Boolean
}