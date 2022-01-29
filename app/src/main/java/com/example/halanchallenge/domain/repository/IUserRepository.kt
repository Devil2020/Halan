package com.example.halanchallenge.domain.repository

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse

interface IUserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse

    fun isLoggedIn(): Boolean

    fun changeLoggingStatus(isLoggedIn: Boolean)

    fun saveToken(token: String)

    fun loadToken(): String

    fun saveProfile(profile: LoginResponse.Profile)

    fun getProfile(): LoginResponse.Profile

    fun logOutUser()

}