package com.example.halanchallenge.utils.usecase

import com.morse.helper_ui_viewmodel_usecase.usecase.ExceptionType

sealed class State <out T> {

    object Loading : State<Nothing> ()

    data class Success<out T>(val data: T) : State<T>()

    object Empty : State<Nothing> ()

    data class Error (
        val exceptionType: ExceptionType
    ) : State<Nothing>()

}