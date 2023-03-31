package com.notiprice.entity

import javax.persistence.*

@Entity
@Table(name = "sanctions")
open class Sanction() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "type")
    open var type: String? = null

    @Column(name = "value")
    open var value: String? = null

    @Column(name = "description", length = 1000)
    open var description: String? = null

    constructor(id: Long?, type: String?, value: String?, description: String?) : this() {
        this.id = id
        this.type = type
        this.value = value
        this.description = description
    }
}