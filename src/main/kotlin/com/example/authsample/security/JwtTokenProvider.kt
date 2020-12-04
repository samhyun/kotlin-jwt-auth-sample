package com.example.authsample.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(
        private val customUserDetailsService: CustomUserDetailsService,
        @Value("\${jwt.secret}")
        private val jwtSecret: String = "",
        @Value("\${jwt.expiration}")
        val jwtExpirationInMs: Long = 900000,
        @Value("\${jwt.header}")
        private val tokenHeader: String,
        @Value("\${jwt.header-prefix}")
        private val tokenHeaderPrefix: String
) {
    companion object : KLogging()

    fun generate(userDetails: UserDetails): String {
        return generate(userDetails.username)
    }

    fun generate(email: String): String {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMs)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact()
    }

    fun validate(authToken: String): Boolean {
        return try {
            val claims: Jws<Claims> = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUserDetails(jwt: String): UserDetails {
        return customUserDetailsService.loadUserByUsername(getEmailFromJwt(jwt))
    }

    fun getJwt(request: HttpServletRequest): String {
        return try {
            request.getHeader(tokenHeader).replace(tokenHeaderPrefix, "")
        } catch (e: Exception) {
            logger.error { "occurred error!! not found jwt header : $e" }
            ""
        }

    }

    private fun getEmailFromJwt(token: String): String {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body.subject
    }

}