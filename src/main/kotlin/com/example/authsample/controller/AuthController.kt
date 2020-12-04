package com.example.authsample.controller

import com.example.authsample.controller.payload.LoginRequest
import com.example.authsample.domain.User
import com.example.authsample.dto.UserDto
import com.example.authsample.service.AuthService
import com.example.authsample.service.UserService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/auth")
class AuthController(
        private val authService: AuthService,
        private val userService: UserService,
) {

    @PostMapping("/join")
    fun join(@RequestBody user: UserDto): User {
        return userService.create(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest, response: HttpServletResponse): String {
        return authService.login(loginRequest)
    }

    @PostMapping("/logout")
    fun logout() {
        //TODO logout 기능 구현
    }

    @PutMapping("/token/refresh")
    fun refreshToken(): String {
        //TODO 토큰 갱신 기능 구현
        return ""
    }

    @GetMapping("/token/expiration")
    fun getTokenExpiration(): Long {
        //TODO 토큰 만료 기한 조회 기능 구현
        return 0
    }

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }
}