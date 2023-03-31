package com.notiprice.controller

import com.notiprice.dto.UserDto
import com.notiprice.service.UserService
import org.springframework.web.bind.annotation.*

/**
 * Контроллер пользователей.
 */
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    /**
     * Изменение данных о пользователе.
     */
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: UserDto) {

        userService.updateUser(user.toEntity())
    }

    /**
     * Удаление пользователя.
     */
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {

        userService.deleteProduct(id)
    }

    /**
     * Получение данных о пользователе по пользовательскому имени.
     */
    @GetMapping("/get")
    fun getUserByUsername(@RequestParam username: String): UserDto {

        return userService.getUserByUsername(username).toDto()
    }
    /**
     * Получение данных о пользователе по идентификатору.
     */
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDto {

        return userService.getProductById(id).toDto()
    }
}