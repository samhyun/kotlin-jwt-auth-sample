package com.example.authsample.service

import com.example.authsample.common.exception.UserAlreadyExistsException
import com.example.authsample.domain.User
import com.example.authsample.dto.UserDto
import com.example.authsample.domain.repository.UserRepository
import mu.KLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
){

    companion object : KLogging()

    private fun exist(user: UserDto): Boolean {
        return userRepository.existsByEmail(user.email)
    }

    @Throws(UserAlreadyExistsException::class)
    fun create(user: UserDto): User {
        if (exist(user)) {
            logger.warn { "already registered user email" }
            throw UserAlreadyExistsException()
        }
        return save(User(user, passwordEncoder))
    }

    private fun save(user: User): User {
        return userRepository.save(user)
    }

}