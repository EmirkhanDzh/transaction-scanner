package com.notiprice.entity

import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "transactions")
open class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "client_from")
    open var clientFrom: String? = null

    @Column(name = "client_to")
    open var clientTo: String? = null

    @Column(name = "amount")
    open var amount: Long? = null

    @Column(name = "bank_from")
    open var bankFrom: String? = null

    @Column(name = "bank_to")
    open var bankTo: String? = null

    @Column(name = "pay_system_from")
    open var paySystemFrom: String? = null

    @Column(name = "pay_system_to")
    open var paySystemTo: String? = null

    @Column(name = "transfer_date")
    open var transferDate: LocalDateTime? = null

    @Column(name = "country_from")
    open var countryFrom: String? = null

    @Column(name = "country_to")
    open var countryTo: String? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operator_result_id")
    open var operatorResult: OperatorsResult? = null
}