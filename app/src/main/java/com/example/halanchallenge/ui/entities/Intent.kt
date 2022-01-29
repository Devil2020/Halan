package com.example.halanchallenge.ui.entities

import com.example.halanchallenge.domain.entities.login.LoginResponse

sealed class Intent {

    object Login : Intent()

    data class MakeItLogggedIn (val isLoggedIn : Boolean) : Intent()

    data class SaveToken(val token: String) : Intent()

    data class SaveProfile(val profile: LoginResponse.Profile) : Intent()

    object GetProducts : Intent()
}
