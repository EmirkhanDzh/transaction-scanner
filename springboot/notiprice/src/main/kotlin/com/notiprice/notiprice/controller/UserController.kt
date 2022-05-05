package com.notiprice.notiprice.controller

import com.notiprice.notiprice.dto.UserDto
import com.notiprice.notiprice.dto.toEntity
import com.notiprice.notiprice.entity.User
import com.notiprice.notiprice.entity.toDto
import com.notiprice.notiprice.service.UserService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {
    @PostMapping
    fun addUser(@RequestBody user: UserDto): UserDto {

        return userService.addUser(user.toEntity()).toDto()
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: UserDto) {

        userService.updateProduct(user.toEntity())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {

        userService.deleteProduct(id)
    }

    @GetMapping
    fun getUsers(): List<UserDto> {

        return userService.getAllUsers().map(User::toDto)
    }

    @PostMapping("/login")
    fun login(@RequestBody user: UserDto): UserDto {

        return userService.login(user.toEntity()).toDto()
    }

    @GetMapping("/get")
    fun getUserByUsername(@RequestParam username: String): UserDto {

        return userService.getUserByUsername(username).toDto()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDto {

        return userService.getProductById(id).toDto()
    }
}