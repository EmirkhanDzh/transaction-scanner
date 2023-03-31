package com.notiprice.extension

import com.notiprice.dto.OperatorDto
import com.notiprice.dto.OperatorResultDto
import com.notiprice.dto.RulesEngineResultDto
import com.notiprice.dto.SanctionDto
import com.notiprice.dto.TransactionDto
import com.notiprice.entity.Operator
import com.notiprice.entity.OperatorsResult
import com.notiprice.entity.RulesEngineResult
import com.notiprice.entity.Sanction
import com.notiprice.entity.Transaction

fun TransactionDto.toEntity() = Transaction(
    id = this.id,
    clientFrom = this.clientFrom,
    clientTo = this.clientTo,
    amount = this.amount,
    bankFrom = this.bankFrom,
    bankTo = this.bankTo,
    paySystemFrom = this.paySystemFrom,
    paySystemTo = this.paySystemTo,
    transferDate = this.transferDate,
    countryFrom = this.countryFrom,
    countryTo = this.countryTo,
    operatorResult = this.operatorResult?.toEntity(),
)

fun Transaction.toDto() = TransactionDto(
    id = this.id,
    clientFrom = this.clientFrom,
    clientTo = this.clientTo,
    amount = this.amount,
    bankFrom = this.bankFrom,
    bankTo = this.bankTo,
    paySystemFrom = this.paySystemFrom,
    paySystemTo = this.paySystemTo,
    transferDate = this.transferDate,
    countryFrom = this.countryFrom,
    countryTo = this.countryTo,
    operatorResult = this.operatorResult?.toDto(),
)

fun OperatorResultDto.toEntity() = OperatorsResult(
    id = this.id,
    isClear = this.isClear,
    comment = this.comment,
    operator = this.operator?.toEntity(),
    rulesEngineResult = this.rulesEngineResult?.toEntity(),
)

fun OperatorsResult.toDto() = OperatorResultDto(
    id = this.id,
    isClear = this.isClear,
    comment = this.comment,
    operator = this.operator?.toDto(),
    rulesEngineResult = this.rulesEngineResult?.toDto(),
)

fun OperatorDto.toEntity() = Operator(
    id = this.id,
    username = this.username,
    password = this.password,
)

fun Operator.toDto() = OperatorDto(
    id = this.id,
    username = this.username,
    password = this.password,
)

fun RulesEngineResultDto.toEntity() = RulesEngineResult(
    id = this.id,
    isClear = this.isClear,
    sanction = this.sanction?.toEntity(),
)

fun RulesEngineResult.toDto() = RulesEngineResultDto(
    id = this.id,
    isClear = this.isClear,
    sanction = this.sanction?.toDto(),
)

fun SanctionDto.toEntity() = Sanction(
    id = this.id,
    type = this.type,
    value = this.value,
    description = this.description,
)

fun Sanction.toDto() = SanctionDto(
    id = this.id,
    type = this.type,
    value = this.value,
    description = this.description,
)