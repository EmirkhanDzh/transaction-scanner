package com.notiprice.controller

import com.notiprice.extension.toEntity
import com.notiprice.dao.OperatorRepository
import com.notiprice.dto.OperatorDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OperatorController(
    private val operatorRepository: OperatorRepository,
) {
    val operators = listOf(
        OperatorDto(
            id = 1,
            username = "operator_1",
            password = "pwd1",
        ),
        OperatorDto(
            id = 2,
            username = "operator_2",
            password = "pwd2",
        ),
        OperatorDto(
            id = 3,
            username = "operator_3",
            password = "pwd3",
        )
    )

    @PostMapping("/operators/save")
    fun saveOperators(@RequestBody requestOperatorDtoList: List<OperatorDto>) {
        System.err.println(requestOperatorDtoList)
        operatorRepository.saveAll(requestOperatorDtoList.map {
            it.toEntity()
        })
    }
}