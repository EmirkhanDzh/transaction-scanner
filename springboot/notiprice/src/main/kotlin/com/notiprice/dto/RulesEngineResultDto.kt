package com.notiprice.dto

import com.notiprice.entity.RulesEngineResult

data class RulesEngineResultDto(
    var id: Long? = null,
    var isClear: Boolean? = null,
    var sanctionDto: SanctionDto? = null,
) {
//    fun toEntity() = RulesEngineResult(
//        id,
//        transactionId,
//        isClear,
//        clientSanctionId,
//        bankSanctionId,
//        paysystemSanctionId,
//        countrySanctionId,
//    )
}
