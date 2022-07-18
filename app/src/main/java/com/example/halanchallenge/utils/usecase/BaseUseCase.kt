package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.utils.usecase.State
import com.morse.helper_ui_viewmodel_usecase.usecase.toExceptionType
import kotlinx.coroutines.flow.*

abstract class UseCase() {

    fun <SuccessModel> executeFlowUseCase(`do`: () -> Flow<SuccessModel>): Flow<State<SuccessModel>> {
        return `do`.invoke().map {
            State.Success(it) as State<SuccessModel>
        }
            .onStart { emit(State.Loading) }
            .onEmpty { emit(State.Empty) }
            .catch { e ->
                emit(
                    State.Error(
                        e.toExceptionType()
                    )
                )
            }
    }

    fun <SuccessModel> executeSuspendUseCase(`do`: suspend () -> SuccessModel): Flow<State<SuccessModel>> {
        return flow {
            val result = `do`.invoke()
            emit(result)
        }.map {
            State.Success(it) as State<SuccessModel>
        }
            .onStart { emit(State.Loading) }
            .onEmpty { emit(State.Empty) }
            .catch { e ->
                emit(
                    State.Error(
                        e.toExceptionType()
                    )
                )
            }
    }

    fun <SuccessModel> executeUseCase(`do`: suspend () -> SuccessModel): Flow<SuccessModel> {
        return flow {
            val result = `do`.invoke()
            emit(result)
        }
    }
}