package com.notiprice.exception

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.ResourceAccessException

private val log = KotlinLogging.logger {}

/**
 * Перехватывает исключение, если они возникают в результате вызова контроллера.
 */
@RestControllerAdvice
class ControllerExceptionHandler {

    /**
     * Перехватывает исключения типа IllegalArgumentException.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoSuchElementException(e: IllegalArgumentException): Map<String, String> {
        log.warn(e.message, e)
        return errorResponse(e)
    }

    /**
     * Перехватывает исключения типа NoSuchElementException.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoSuchElementException(e: NoSuchElementException): Map<String, String> {
        log.warn(e.message, e)
        return errorResponse(e)
    }

    /**
     * Перехватывает исключения типа ResourceAccessException.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceAccessException(e: ResourceAccessException): Map<String, String> {
        log.warn(e.message, e)
        return errorResponse(e)
    }

    /**
     * Перехватывает исключения типа Exception.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): Map<String, String> {
        log.warn(e.message, e)
        return errorResponse(e)
    }

    /**
     * Создание ответа с информацией об ошибке.
     */
    private fun errorResponse(e: Exception): Map<String, String> = mapOf(
        "status" to "error",
        "exception" to e.javaClass.simpleName,
        "message" to e.message.orEmpty()
    )
}