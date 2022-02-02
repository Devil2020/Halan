package com.example.halanchallenge.ui.auth

import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.utils.base.Intent

sealed class LoginIntents : Intent{

    object Login : LoginIntents()

    data class MakeItLogggedIn (val isLoggedIn : Boolean) : LoginIntents()

    data class SaveToken(val token: String) : LoginIntents()

    data class SaveProfile(val profile: LoginResponse.Profile) : LoginIntents()


}
