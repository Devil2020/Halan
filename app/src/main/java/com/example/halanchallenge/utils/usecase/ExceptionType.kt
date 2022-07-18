package com.morse.helper_ui_viewmodel_usecase.usecase

import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ExceptionType() {
    object HttpException404 : ExceptionType()
    object HttpException422 : ExceptionType()
    object HttpException403 : ExceptionType()
    object HttpException504 : ExceptionType()
    object HttpException401 : ExceptionType()
    object HttpException400 : ExceptionType()
    object HttpExceptionGeneric : ExceptionType()
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

fun Throwable.toExceptionType(): ExceptionType {
    return when (this) {
        is SocketTimeoutException -> return ExceptionType.SocketTimeoutException
        is javax.net.ssl.SSLHandshakeException -> return ExceptionType.SSLHandshakeException
        is UnknownHostException -> return ExceptionType.UnknownHostException
        is java.net.ProtocolException -> return ExceptionType.ProtocolException
        is javax.net.ssl.SSLException -> return ExceptionType.SSLException
        is java.net.SocketException -> return ExceptionType.SocketException
        is java.io.EOFException -> return ExceptionType.EOFException
        is java.util.concurrent.CancellationException -> return ExceptionType.UserCancellationException
        else -> return ExceptionType.GenericException(localizedMessage ?: "")
    }
}