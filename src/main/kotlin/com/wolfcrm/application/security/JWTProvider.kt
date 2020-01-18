package com.wolfcrm.application.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTProvider {
    @Value("\${security.jwt.token.secret-key}")
    private var secretKey: String? = null

    @Value("\${security.jwt.token.expire-length}")
    private val validityInMilliseconds: Long = 3600000

    fun createToken(username: String): String {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    fun getUsername(token: String): String {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .body.subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            throw Exception("Expired or invalid JWT token")
        } catch (e: IllegalArgumentException) {
            throw Exception("Expired or invalid JWT token")
        }
    }

}