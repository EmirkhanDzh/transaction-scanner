package com.notiprice.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils.hasText
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

private const val AUTHORIZATION = "Authorization"

/**
 * Фильтр для поступающих в программу запросов для авторизации.
 */
@Component
class JwtFilter(
    /**
     * Класс для обработки токена.
     */
    private val jwtProvider: JwtProvider,
    /**
     * Сервис для класса CustomUserDetails.
     */
    private val customUserDetailsService: CustomUserDetailsService
) : GenericFilterBean() {
    /**
     * Фильтр для поступающих в программу запросов для авторизации.
     */
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val token = getTokenFromRequest(request as HttpServletRequest)

        if(token != null && jwtProvider.validateToken(token)) {


            try {
                val userLogin = jwtProvider.getLoginFromToken(token)

                val customUserDetails = customUserDetailsService.loadUserByUsername(userLogin)

                val auth =
                    UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.authorities)

                SecurityContextHolder.getContext().authentication = auth
            }catch (_: IllegalArgumentException) {
            }

        }

        chain!!.doFilter(request, response)
    }

    /**
     * Получение токена из запроса.
     */
    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearer = request.getHeader(AUTHORIZATION)

        if(hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7)
        }

        return null
    }
}