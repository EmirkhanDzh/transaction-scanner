package com.notiprice.dao;

import com.notiprice.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Long> {
    fun findByAdminUsername(adminUsername: String?): Admin?
}