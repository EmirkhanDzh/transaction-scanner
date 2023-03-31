package com.notiprice.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

/**
 * Класс для обработки токена.
 */
@Component
class JwtProvider(
    /**
     * Секретный ключ для шифрования.
     */
    @Value("\${jwt.secret}") private val secret: String
) {
    /**
     * Генерация токена.
     */
    fun generateToken(login: String): String {
        val date = Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant())

        return Jwts.builder()
            .setSubject(login)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    /**
     * Валидация токена.
     */
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (ex: Exception) {
            false
        }
    }

    /**
     * Получение пользовательского имени из токена.
     */
    fun getLoginFromToken(token: String) : String {
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body

        return claims.subject
    }
}