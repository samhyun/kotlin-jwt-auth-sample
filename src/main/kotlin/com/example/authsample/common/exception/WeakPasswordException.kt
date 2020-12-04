package com.example.authsample.common.exception

import java.lang.RuntimeException

class WeakPasswordException: RuntimeException {
    constructor(): super()
    constructor(message: String): super()
}