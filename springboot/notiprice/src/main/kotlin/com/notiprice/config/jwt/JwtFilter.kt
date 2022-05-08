package com.notiprice.config.jwt

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

@Component
class JwtFilter(
    private val jwtProvider: JwtProvider,
    private val customUserDetailsService: CustomUserDetailsService
) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        //println("filter...")

        val token = getTokenFromRequest(request as HttpServletRequest)

        if(token != null && jwtProvider.validateToken(token)) {
            val userLogin = jwtProvider.getLoginFromToken(token)

            val customUserDetails = customUserDetailsService.loadUserByUsername(userLogin)

            val auth =
                UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.authorities)

            SecurityContextHolder.getContext().authentication = auth
        } else {
            println("forbitten...")
        }

        chain!!.doFilter(request, response)
    }

    fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearer = request.getHeader(AUTHORIZATION)

        if(hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7)
        }

        return null
    }
}