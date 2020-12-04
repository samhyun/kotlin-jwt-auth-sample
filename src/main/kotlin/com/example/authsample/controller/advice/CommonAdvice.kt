package com.example.authsample.controller.advice

import com.example.authsample.common.exception.UserAlreadyExistsException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class CommonAdvice {

    companion object : KLogging()

    @ExceptionHandler(Exception::class)
    fun handleUserAlreadyExistsException(e: UserAlreadyExistsException, response: HttpServletResponse) : String {
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        return e.javaClass.simpleName
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(e: BadCredentialsException, response: HttpServletResponse) : String {
        response.status = HttpStatus.UNAUTHORIZED.value()
        logger.error { "wrong email or password !!! e : $e " }
        return e.javaClass.simpleName
    }
}