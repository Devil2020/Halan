package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.toExceptionType
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

/*                    flow<State> {
                        val result = executeLoginUserUseCase(
                            vm.repository, LoginRequest(
                                username = vm.userNameValidator.value ?: "",
                                password = vm.passwordValidator.value ?: ""
                            )
                        )
                        emit(State.Success(result))
                    }
                        .onStart {
                            emit(State.Loading)
                        }
                        .onEmpty {
                            emit(State.Empty)
                        }
                        .catch {
                            val exception = it
                            emit(State.Error(it.toExceptionType(), ""))
                        }.onEach {
                            render(it)
                        }
                        .launchIn(lifecycleScope)*/

fun executeLoginUserUseCase(repository: IUserRepository, request: LoginRequest) =
    flow<State> {
        val result = repository.loginUser(request)
        emit(State.Success(result))
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
