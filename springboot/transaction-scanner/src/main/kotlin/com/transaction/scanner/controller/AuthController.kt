package com.transaction.scanner.controller

import com.transaction.scanner.entity.Admin
import com.transaction.scanner.entity.Operator
import com.transaction.scanner.service.AuthService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.*


/**
 * Контроллер для аутентификации и регистрации.
 */
@RestController
class AuthController(
    private val authService: AuthService,
) {
    /**
     * Регистрация оператора.
     */
    @PostMapping("/operator/sign-up")
    fun addUser(@RequestBody user: Operator): Operator =
        authService.addUser(user)

    /**
     * Проверяет пароль пользователя, если пароли совпадают, возвращает токен, если нет, то бросает исключение.
     */
    @PostMapping("/auth/operator/sign-in")
    fun login(@RequestBody user: Operator): ResponseEntity<*> {

        return try {
            ResponseEntity.ok().body(
                authService.login(user)
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
            ResponseEntity.ok().body(
                authService.adminLogin(user)
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