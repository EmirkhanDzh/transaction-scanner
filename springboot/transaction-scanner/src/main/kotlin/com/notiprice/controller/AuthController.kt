package com.notiprice.controller

//import com.notiprice.security.JwtProvider

import com.notiprice.dao.AdminRepository
import com.notiprice.dao.OperatorRepository
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
class AuthController(
    private val operatorService: OperatorService,
    private val adminService: AdminService,
    private val authManager: AuthenticationManager,
    private val jwtUtil: JwtTokenUtil,
    private val operatorRepository: OperatorRepository,
    private val adminRepository: AdminRepository,

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

//    /**
//     * Регистрация пользователя.
//     */
//    @PostMapping("/admin/sign-up")
//    fun addAdmin(@RequestBody user: Admin): Admin {
//        val savedUser = adminService.addUser(user)
//        savedUser.adminPassword = ""
//        return savedUser
//    }

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает токен, если нет, то бросает исключение.
     */
    @PostMapping("/auth/operator/sign-in")
    fun login(@RequestBody user: Operator): ResponseEntity<*> {
//        return ResponseEntity.ok().body("accessToken")

        return try {

            val token = UsernamePasswordAuthenticationToken(user.username, user.password)

            val authentication: Authentication = authManager.authenticate(
                token
            )

            val accessToken = jwtUtil.generateAccessToken(authentication.principal as AuthUser)

            val entity = operatorRepository.findByOperatorUsername(user.username)!!

            ResponseEntity.ok().body(
                mapOf(
                    "token" to accessToken,
                    "operatorId" to entity.id,
                    "operatorUsername" to entity.username
                )
            )
        } catch (ex: BadCredentialsException) {
            logger.warn { ex.message }
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<String>()
        } catch (th: Throwable) {
            logger.error { th.message }
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<String>()
        }
    }

    @PostMapping("/auth/admin/sign-in")
    fun loginAdmin(@RequestBody user: Admin): ResponseEntity<*> {
        return try {

            val token = UsernamePasswordAuthenticationToken(user.username, user.password)

            val authentication: Authentication = authManager.authenticate(
                token
            )

            val entity = adminRepository.findByAdminUsername(user.username)!!

            val accessToken = jwtUtil.generateAccessToken(authentication.principal as AuthUser)
            ResponseEntity.ok().body(
                mapOf(
                    "token" to accessToken,
                    "adminId" to entity.id,
                    "adminUsername" to entity.username
                )
            )
        } catch (ex: BadCredentialsException) {
            logger.warn { ex.message }
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<String>()
        } catch (th: Throwable) {
            logger.error { th.message }
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<String>()
        }
    }

    private companion object {
        val logger = KotlinLogging.logger {  }
    }
}