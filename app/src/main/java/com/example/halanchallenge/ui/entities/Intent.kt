package com.example.halanchallenge.ui.entities

sealed class Intent {

    data class Login(val userName: String, val password: String) : Intent()

    object GetProducts : Intent()
}
