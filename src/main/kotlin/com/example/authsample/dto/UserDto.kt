package com.example.authsample.dto

data class UserDto(
        val id: Long?,
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String
)
