package com.example.halanchallenge.utils.validator

object InputValidator {

    fun isPasswordValid (password  :String) : Boolean {
        return password.isNotEmpty()
    }

    fun isUsernameValid (username  :String) : Boolean {
        return username.matches("^[a-zA-Z0-9]*$".toRegex()) and ( username.length in ( 6 .. 15 ))
    }

}