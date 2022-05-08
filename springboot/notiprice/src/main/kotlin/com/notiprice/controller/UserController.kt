package com.notiprice.controller

import com.notiprice.dto.UserDto
import com.notiprice.dto.toEntity
import com.notiprice.entity.toDto
import com.notiprice.service.UserService
import org.springframework.web.bind.annotation.*

//@CrossOrigin(origins = ["http://localhost:3000"])
//@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

//    @PostMapping
//    fun addUser(@RequestBody user: UserDto): UserDto {
//
//        return userService.addUser(user.toEntity()).toDto()
//    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: UserDto) {

        userService.updateProduct(user.toEntity())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {

        userService.deleteProduct(id)
    }

//    @PostMapping("/login")
//    fun login(@RequestBody user: UserDto): UserDto {
//
//        return userService.login(user.toEntity()).toDto()
//    }

    @GetMapping("/get")
    fun getUserByUsername(@RequestParam username: String): UserDto {

        return userService.getUserByUsername(username).toDto()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserDto {

        return userService.getProductById(id).toDto()
    }
}