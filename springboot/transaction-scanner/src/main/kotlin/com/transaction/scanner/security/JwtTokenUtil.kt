package com.transaction.scanner.security

import com.notiprice.entity.AuthUser
import io.jsonwebtoken.*
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenUtil {
    @Value("\${jwt.secret}")
    private val SECRET_KEY: String? = null
    fun generateAccessToken(user: AuthUser): String {
        return Jwts.builder()
            .setSubject(java.lang.String.format("%s,%s", user.getUserId(), user.username))
            .setIssuer("CodeJava")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }

    fun validateAccessToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
            return true
        } catch (ex: ExpiredJwtException) {
            LOGGER.error("JWT expired", ex.message)
        } catch (ex: IllegalArgumentException) {
            LOGGER.error("Token is null, empty or only whitespace", ex.message)
        } catch (ex: MalformedJwtException) {
            LOGGER.error("JWT is invalid", ex)
        } catch (ex: UnsupportedJwtException) {
            LOGGER.error("JWT is not supported", ex)
        } catch (ex: SignatureException) {
            LOGGER.error("Signature validation failed")
        }
        return false
    }

    fun getSubject(token: String): String? {
        return parseClaims(token)?.subject
    }

    private fun parseClaims(token: String): Claims? {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body
    }

    companion object {
        val LOGGER = KotlinLogging.logger {  }
        private const val EXPIRE_DURATION = (24 * 60 * 60 * 1000 // 24 hour
                ).toLong()
    }
}