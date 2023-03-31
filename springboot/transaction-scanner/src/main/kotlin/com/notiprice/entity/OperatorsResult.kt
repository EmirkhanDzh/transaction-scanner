package com.notiprice.entity

import javax.persistence.*

@Entity
@Table(name = "operators_results")
open class OperatorsResult() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "is_clear")
    open var isClear: Boolean? = null

    @Column(name = "comment", length = 1000)
    open var comment: String? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "operator_id")
    open var operator: Operator? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "rules_engine_result_id")
    open var rulesEngineResult: com.notiprice.entity.RulesEngineResult? = null

    constructor(
        id: Long?,
        isClear: Boolean?,
        comment: String?,
        operator: Operator?,
        rulesEngineResult: RulesEngineResult?
    ) : this() {
        this.id = id
        this.isClear = isClear
        this.comment = comment
        this.operator = operator
        this.rulesEngineResult = rulesEngineResult
    }
}