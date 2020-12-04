package com.example.authsample.security

import com.example.authsample.domain.User

class CustomUserDetails(user: User) : org.springframework.security.core.userdetails.User(user.email, user.password, mutableListOf()) {

}
