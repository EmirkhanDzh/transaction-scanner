package com.notiprice.controller

import com.notiprice.dao.OperatorRepository
import com.notiprice.dto.OperatorDto
import com.notiprice.extension.toDto
import org.springframework.web.bind.annotation.*

/**
 * Контроллер пользователей.
 */
@RestController
@RequestMapping("/operator")
class UserController(private val operatorRepository: OperatorRepository) {


    /**
     * Получение данных о пользователе по пользовательскому имени.
     */
    @GetMapping
    fun getUserByUsername(@RequestParam username: String): OperatorDto {

        return operatorRepository.findByOperatorUsername(username)!!.apply { operatorPassword = "" }.toDto()
    }
    /**
     * Получение данных о пользователе по идентификатору.
     */
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): OperatorDto {

        return operatorRepository.findById(id).get().toDto()
    }
}