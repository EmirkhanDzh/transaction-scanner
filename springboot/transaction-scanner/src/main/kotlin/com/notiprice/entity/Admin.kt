package com.notiprice.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "admins")
open class Admin() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "username")
    open var username: String? = null

    @Column(name = "password")
    open var password: String? = null

    constructor(id: Long?, username: String?, password: String?) : this() {
        this.id = id
        this.username = username
        this.password = password
    }
}