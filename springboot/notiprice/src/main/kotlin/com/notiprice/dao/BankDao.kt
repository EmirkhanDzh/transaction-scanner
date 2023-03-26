package com.notiprice.dao

import com.notiprice.entity.Bank
import com.notiprice.entity.Client

interface BankDao {
    fun findByIdOrNull(id: Long): Bank?
}