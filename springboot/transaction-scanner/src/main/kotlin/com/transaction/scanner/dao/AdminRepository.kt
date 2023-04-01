package com.transaction.scanner.dao;

import com.transaction.scanner.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Long> {
    fun findByAdminUsername(adminUsername: String?): Admin?
}