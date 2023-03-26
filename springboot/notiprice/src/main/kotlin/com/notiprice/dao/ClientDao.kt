package com.notiprice.dao

import com.notiprice.dto.ClientDto
import com.notiprice.entity.Client
import com.notiprice.entity.Transaction

interface ClientDao {
    fun findByIdOrNull(id: Long): Client?
}