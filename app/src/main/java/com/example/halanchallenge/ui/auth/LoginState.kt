package com.example.halanchallenge.ui.auth

import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.utils.base.State

val InitialLoginState = LoginState(null , null , null)
val LoginLoadingState = LoginState ( isLoading = true )
fun Throwable.toLoginErrorState () = LoginState(
    isLoading = null ,
    loginResponse = null ,
    error = this
)
data class LoginState(
    val isLoading : Boolean ?= null ,
    val error : Throwable?= null ,
    val loginResponse : LoginResponse ?= null
) : State
