package com.example.authsample.common.exception

import java.lang.RuntimeException

class UserAlreadyExistsException: RuntimeException {
    constructor(): super()
    constructor(message: String): super()
}
