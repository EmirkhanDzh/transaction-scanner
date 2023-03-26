package com.notiprice.dao

import com.notiprice.dto.PaysystemDto
import com.notiprice.entity.Country
import com.notiprice.entity.Paysystem

interface PaysystemDao {
    fun findByIdOrNull(id: Long): Paysystem?
}