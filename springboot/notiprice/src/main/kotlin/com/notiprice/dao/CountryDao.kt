package com.notiprice.dao

import com.notiprice.dto.CountryDto
import com.notiprice.entity.Client
import com.notiprice.entity.Country

interface CountryDao {
    fun findByIdOrNull(id: Long): Country?
}