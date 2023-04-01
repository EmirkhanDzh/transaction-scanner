package com.notiprice.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "operators")
class Operator(): AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "username")
    var operatorUsername: String? = null

    @Column(name = "password")
    var operatorPassword: String? = null

    constructor(id: Long?, username: String?, password: String?) : this() {
        this.id = id
        this.operatorUsername = username
        this.operatorPassword = password
    }

    override fun getUserId(): Long? {
        return id
    }


    override fun getPassword(): String? {
        return operatorPassword
    }

    override fun getUsername(): String? {
        return operatorUsername
    }

}