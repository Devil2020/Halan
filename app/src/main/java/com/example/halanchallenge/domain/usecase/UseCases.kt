package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.login.LoginRequest
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
        .onStart { emit(State.Loading) }
        .onEmpty { emit(State.Empty) }
        .catch {
            emit(State.Error(it.toExceptionType() , ""))
        }