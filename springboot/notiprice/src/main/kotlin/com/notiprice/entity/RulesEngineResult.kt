package com.notiprice.entity

import com.notiprice.dto.RulesEngineResultDto

data class RulesEngineResult(
    val id: Long = 0,
    val transactionId: Long = 0,
    val isClear: Boolean = true,
    val clientSanctionId: Long? = null,
    val bankSanctionId: Long? = null,
    val paysystemSanctionId: Long? = null,
    val countrySanctionId: Long? = null,
) {
//    fun toDto() = RulesEngineResultDto(
//        id,
//        transactionId,
//        isClear,
//        clientSanctionId,
//        bankSanctionId,
//        paysystemSanctionId,
//        countrySanctionId,
//    )
}