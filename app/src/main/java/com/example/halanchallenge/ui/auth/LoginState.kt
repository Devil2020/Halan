package com.example.halanchallenge.ui.auth

import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.utils.base.State

val InitialLoginState = LoginState(null , null , null)

data class LoginState(
    val isLoading : Boolean ? ,
    val error : Throwable? ,
    val loginResponse : LoginResponse ?
) : State
