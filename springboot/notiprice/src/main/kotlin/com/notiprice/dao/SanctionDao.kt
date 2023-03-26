package com.notiprice.dao

import com.notiprice.entity.Sanction

interface SanctionDao {

    fun getAll(): List<Sanction>
}