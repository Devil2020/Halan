package com.example.halanchallenge.data.repository

import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IUserRepository

class UserRepository(private val remoteGateway: IRemoteGateway) : IUserRepository {

    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return remoteGateway.login(loginRequest)
    }
}