package com.notiprice.config.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Component
class JwtProvider(
    @Value("\${jwt.secret}") private val secret: String
) {

    fun generateToken(login: String): String {
        val date = Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant())

        return Jwts.builder()
            .setSubject(login)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun getLoginFromToken(token: String) : String {
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body

        return claims.subject
    }
}