//package com.notiprice.dto
//
//import com.notiprice.entity.Result
//import com.notiprice.entity.Sanction
//
//open class ResultDto(
//    open val id: Long = 0,
//    open val transactionId: Long = 0,
//    open val isClear: Boolean = true,
//) {
//    open fun toEntity() = Result(
//        id,
//        transactionId,
//        isClear,
//        clientSanctionId,
//        bankSanctionId,
//        paysystemSanctionId,
//        countrySanctionId,
//    )
//}