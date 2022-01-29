package com.example.halanchallenge.ui.entities

sealed class State {

    object Nothing : State()

    object Loading : State()

    data class Success<out T>(val data: T) : State()

    object Empty : State()

    data class Error(
        val exceptionType: ExceptionType,
        val message: String
    ) : State()

}

sealed class ExceptionType {
    object SocketTimeoutException : ExceptionType()
    object SSLHandshakeException : ExceptionType()
    object UnknownHostException : ExceptionType()
    object ProtocolException : ExceptionType()
    object SSLException : ExceptionType()
    object SocketException : ExceptionType()
    object EOFException : ExceptionType()
    object UserCancellationException : ExceptionType()
    data class GenericException(val message: String) : ExceptionType()
}