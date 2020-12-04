package com.example.authsample.service

import com.example.authsample.controller.payload.LoginRequest
import com.example.authsample.security.CustomUserDetails
import com.example.authsample.security.JwtTokenProvider
import mu.KLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthService(
        private val jwtTokenProvider: JwtTokenProvider,
        private val authenticationManager: AuthenticationManager
) {
    companion object : KLogging()

    fun authenticate(loginRequest: LoginRequest): Authentication {
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password))
    }

    @Throws(Exception::class)
    fun login(authRequest: LoginRequest): String {
        val auth: Authentication = authenticate(authRequest)

        SecurityContextHolder.getContext().authentication = auth
        return jwtTokenProvider.generate(auth.principal as CustomUserDetails)
    }
}