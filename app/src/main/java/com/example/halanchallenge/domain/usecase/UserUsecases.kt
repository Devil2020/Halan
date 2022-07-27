package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.utils.validator.InputValidator
import kotlinx.coroutines.flow.*

abstract class IUserGateway : UseCase() {
    /*We must to make the validation for the login here , if the username valid or not*/
    abstract fun executeLoginUseCase(userName: String, password: String): Flow<LoginResponse>
    abstract fun executeSaveTokenUseCase(token: String)
    abstract fun executeLoadTokenUseCase(): String
    abstract fun executeLogoutUseCase()
    abstract fun executeSaveProfileUseCase(profile: LoginResponse.Profile)
    abstract fun executeLoadProfileUseCase(): LoginResponse.Profile
    abstract fun executeGetIsLoggedInUseCase(): Boolean
    abstract fun executeSetIsLoggedInUseCase(isLoggedIn: Boolean)
}

class UserGateway(private val repository: IUserRepository) : IUserGateway() {

    override fun executeLoginUseCase(userName: String, password: String): Flow<LoginResponse> {
        return if (InputValidator.isUsernameValid(userName) && InputValidator.isPasswordValid(
                password
            )
        )
            executeUseCase { repository.loginUser(LoginRequest(userName, password)) }
        else
            flowOf()
    }

    override fun executeSetIsLoggedInUseCase(isLoggedIn: Boolean) {
        repository.changeLoggingStatus(isLoggedIn)
    }

    override fun executeGetIsLoggedInUseCase() = repository.isLoggedIn()

    override fun executeSaveTokenUseCase(token: String) {
        repository.saveToken("Bearer $token")
    }

    override fun executeLoadTokenUseCase() = repository.loadToken()

    override fun executeSaveProfileUseCase(profile: LoginResponse.Profile) {
        repository.saveProfile(profile)
    }

    override fun executeLoadProfileUseCase() = repository.getProfile()

    override fun executeLogoutUseCase() {
        repository.logOutUser()
    }
}