package com.notiprice.entity

import javax.persistence.*

@Entity
@Table(name = "rules_engine_results")
open class RulesEngineResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "is_clear")
    open var isClear: Boolean? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sanction_id")
    open var sanction: Sanction? = null
}