package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.toExceptionType
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart


fun executeLoginUserUseCase(repository: IUserRepository, request: LoginRequest) =
    flow<State> {
        emit(State.Success(repository.loginUser(request)))
    }
        .onStart {
            emit(State.Loading)
        }
        .onEmpty {
            emit(State.Empty)
        }
        .catch {
            val exception = it
            emit(State.Error(exception.toExceptionType(), ""))
        }


fun executeSetIsLoggedInUseCase(repository: IUserRepository, isLoggedIn: Boolean) =
    repository.changeLoggingStatus(isLoggedIn)

fun executeSaveProfileUseCase(repository: IUserRepository, profile: LoginResponse.Profile) =
    repository.saveProfile(profile)

fun executeGetProfileUseCase(repository: IUserRepository) = repository.getProfile()

fun executeLogOutUseCase(repository: IUserRepository) = repository.logOutUser()

fun executeSaveUserTokenUseCase(repository: IUserRepository, token: String) =
    repository.saveToken(token)

fun executeGetUserTokenUseCase(repository: IUserRepository) = repository.loadToken()