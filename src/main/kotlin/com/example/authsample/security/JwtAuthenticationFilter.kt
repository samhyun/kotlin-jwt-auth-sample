package com.example.authsample.security

import mu.KLogger
import mu.KLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        private val jwtTokenProvider: JwtTokenProvider,
): OncePerRequestFilter() {

    companion object : KLogging()

    @Throws(Exception::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt: String = jwtTokenProvider.getJwt(request)

            if (StringUtils.hasText(jwt) && jwtTokenProvider.validate(jwt)) {
                val userDetails: UserDetails = jwtTokenProvider.getUserDetails(jwt)

                val authentication = UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.authorities)

                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("occurred exception in JwtAuthenticationFilter.doFilterInternal : $e")
            throw e
        }

        filterChain.doFilter(request, response)
    }
}