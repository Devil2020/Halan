package com.example.halanchallenge.utils.validator

object InputValidator {

    fun isPasswordValid(password: String? = null): Boolean {

        return password?.isNotEmpty() ?: false
    }

    fun isUsernameValid(username: String? = null): Boolean {
        return username?.matches("^[a-zA-Z0-9]*$".toRegex())
            ?.and((username?.length in (6..15)))
            ?: false
    }

}