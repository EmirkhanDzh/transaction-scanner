package com.notiprice.dao

import com.notiprice.entity.Sanction
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
class SanctionDaoImpl: SanctionDao {
    override fun getAll(): List<Sanction> {
        TODO("Not yet implemented")
    }
}