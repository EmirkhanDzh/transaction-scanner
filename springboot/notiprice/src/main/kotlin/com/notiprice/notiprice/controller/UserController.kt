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
    fun addProduct(@RequestBody user: UserDto): UserDto {
        return userService.addProduct(user.toEntity()).toDto()
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody user: UserDto) {
        userService.updateProduct(user.toEntity())
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        userService.deleteProduct(id)
    }

    @GetMapping
    fun getProducts(): List<UserDto> {
        return userService.getAllProducts().map(User::toDto)
    }

    @PostMapping("/login")
    fun getProductByUsername(@RequestBody user: UserDto): UserDto {
        return userService.login(user.toEntity()).toDto()
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): UserDto {
        return userService.getProductById(id).toDto()
    }
}