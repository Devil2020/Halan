package com.example.halanchallenge.data.repository

import com.example.halanchallenge.data.local.ILocalGateway
import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IUserRepository

class UserRepository(
    private val remoteGateway: IRemoteGateway,
    private val localGateway: ILocalGateway
) : IUserRepository {

    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        return remoteGateway.login(loginRequest)
    }

    override fun isLoggedIn(): Boolean {
        return localGateway.getIsLoggedIn()
    }

    override fun changeLoggingStatus(isLoggedIn: Boolean) {
        localGateway.setIsLoggedIn(isLoggedIn)
    }

    override fun saveProfile(profile: LoginResponse.Profile) {
        localGateway.insertProfile(profile)
    }

    override fun saveToken(token: String) {
        localGateway.insertToken(token)
    }

    override fun loadToken(): String {
        return localGateway.getToken()
    }

    override fun getProfile(): LoginResponse.Profile {
        return localGateway.getProfile()
    }

    override fun logOutUser() {
        localGateway.clearPreference()
    }
}