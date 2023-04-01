package com.transaction.scanner.dao;

import com.notiprice.entity.Sanction
import org.springframework.data.jpa.repository.JpaRepository

interface SanctionRepository : JpaRepository<Sanction, Long> {
}