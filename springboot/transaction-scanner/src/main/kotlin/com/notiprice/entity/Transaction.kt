package com.notiprice.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "transactions")
open class Transaction() {
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "operator_result_id")
    open var operatorResult: OperatorsResult? = null

    constructor(
        id: Long?,
        clientFrom: String?,
        clientTo: String?,
        amount: Long?,
        bankFrom: String?,
        bankTo: String?,
        paySystemFrom: String?,
        paySystemTo: String?,
        transferDate: LocalDateTime?,
        countryFrom: String?,
        countryTo: String?,
        operatorResult: OperatorsResult?
    ) : this() {
        this.id = id
        this.clientFrom = clientFrom
        this.clientTo = clientTo
        this.amount = amount
        this.bankFrom = bankFrom
        this.bankTo = bankTo
        this.paySystemFrom = paySystemFrom
        this.paySystemTo = paySystemTo
        this.transferDate = transferDate
        this.countryFrom = countryFrom
        this.countryTo = countryTo
        this.operatorResult = operatorResult
    }
}