package com.example.authsample.controller.payload

class JwtAuthenticationResponse(
        val accessToken: String,
        val expiryDuration: Long
)