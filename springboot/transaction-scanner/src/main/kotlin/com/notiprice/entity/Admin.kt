package com.notiprice.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "admins")
class Admin(): AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "username")
    var adminUsername: String? = null

    @Column(name = "password")
    var adminPassword: String? = null
    override fun getUserId(): Long? {
        return id
    }


    override fun getPassword(): String? {
        return adminPassword
    }

    override fun getUsername(): String? {
        return adminUsername
    }
}