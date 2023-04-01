package com.transaction.scanner.dao;

import com.transaction.scanner.entity.Sanction
import org.springframework.data.jpa.repository.JpaRepository

interface SanctionRepository : JpaRepository<Sanction, Long> {
}