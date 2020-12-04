package com.example.authsample.domain

import com.example.authsample.common.exception.WeakPasswordException
import com.example.authsample.dto.UserDto
import lombok.Builder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Builder
@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long = 0,
        val email: String,
        var password: String,
        val firstName: String,
        val lastName: String,
) {
    constructor(userDto: UserDto, passwordEncoder: PasswordEncoder) : this(userDto.id ?: 0,
            userDto.email, userDto.password, userDto.firstName, userDto.lastName) {
        validate()
        this.password = passwordEncoder.encode(userDto.password)
    }

    private fun validate() {
        if (password.length < 4) {
            throw WeakPasswordException()
        }
        //TODO validation 기능 추가
    }
}
