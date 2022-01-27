package com.example.halanchallenge.domain.repository

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse

interface IUserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse

}