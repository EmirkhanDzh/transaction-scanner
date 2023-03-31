package com.notiprice.scanner

import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction

interface Scanner {
    fun scan(tr: Transaction, sanctions: List<Sanction>): List<Sanction> {

        return sanctions.filter { s ->
            sanctionIndicator(tr, s)
        }
    }

    fun sanctionIndicator(tr: Transaction, s: Sanction): Boolean
}