package com.notiprice.controller

//import com.notiprice.security.JwtProvider

import com.notiprice.entity.Admin
import com.notiprice.entity.AuthUser
import com.notiprice.entity.Operator
import com.notiprice.security.JwtTokenUtil
import com.notiprice.service.AdminService
import com.notiprice.service.OperatorService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


/**
 * Контроллер для аутентификации и регистрации.
 */
@RestController
@RequestMapping("/auth")
class AuthController(
    private val operatorService: OperatorService,
    private val adminService: AdminService,
    private val authManager: AuthenticationManager,
    private val jwtUtil: JwtTokenUtil,

//    private val jwtProvider: JwtProvider
) {
    /**
     * Регистрация пользователя.
     */
    @PostMapping("/operator/sign-up")
    fun addUser(@RequestBody user: Operator): Operator {
        val savedUser = operatorService.addUser(user)
        savedUser.operatorPassword = ""
        return savedUser
    }

    /**
     * Регистрация пользователя.
     */
    @PostMapping("/admin/sign-up")
    fun addAdmin(@RequestBody user: Admin): Admin {
        val savedUser = adminService.addUser(user)
        savedUser.adminPassword = ""
        return savedUser
    }

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает токен, если нет, то бросает исключение.
     */
    @PostMapping("/operator/sign-in")
    fun login(@RequestBody user: Operator): ResponseEntity<String> {
//        return ResponseEntity.ok().body("accessToken")

        return try {

            val token = UsernamePasswordAuthenticationToken(user.username, user.password)

            val authentication: Authentication = authManager.authenticate(
                token
            )

            val accessToken = jwtUtil.generateAccessToken(authentication.principal as AuthUser)
            ResponseEntity.ok().body(accessToken)
        } catch (ex: BadCredentialsException) {
            logger.warn { ex.message }
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        } catch (th: Throwable) {
            logger.error { th.message }
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PostMapping("/admin/sign-in")
    fun loginAdmin(@RequestBody user: Admin): ResponseEntity<String> {
        return try {

            val token = UsernamePasswordAuthenticationToken(user.username, user.password)

            val authentication: Authentication = authManager.authenticate(
                token
            )

            val accessToken = jwtUtil.generateAccessToken(authentication.principal as AuthUser)
            ResponseEntity.ok().body(accessToken)
        } catch (ex: BadCredentialsException) {
            logger.warn { ex.message }
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        } catch (th: Throwable) {
            logger.error { th.message }
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    private companion object {
        val logger = KotlinLogging.logger {  }
    }
}