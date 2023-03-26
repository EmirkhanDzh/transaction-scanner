//package com.notiprice.entity
//
//import com.notiprice.dto.ResultDto
//
//open class Result (
//    open val id: Long = 0,
//    open val transactionId: Long = 0,
//    open val isClear: Boolean = true,
//    open var clientSanctionId: Long? = null,
//    open var bankSanctionId: Long? = null,
//    open var paysystemSanctionId: Long? = null,
//    open var countrySanctionId: Long? = null,
//) {
//    open fun toDto() = ResultDto(
//        id,
//        transactionId,
//        isClear,
//        clientSanctionId,
//        bankSanctionId,
//        paysystemSanctionId,
//        countrySanctionId,
//    )
//}