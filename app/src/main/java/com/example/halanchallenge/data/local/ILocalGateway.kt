package com.example.halanchallenge.data.local

import com.example.halanchallenge.domain.entities.login.LoginResponse

interface ILocalGateway {

    fun setIsLoggedIn (isLoggedIn : Boolean)

    fun getIsLoggedIn(): Boolean

    fun insertToken(token: String)

    fun insertProfile(profile: LoginResponse.Profile)

    fun getToken(): String

    fun getProfile(): LoginResponse.Profile

    fun clearPreference ()

}