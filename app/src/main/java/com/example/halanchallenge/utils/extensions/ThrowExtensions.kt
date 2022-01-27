package com.example.halanchallenge.utils

import com.example.halanchallenge.ui.entities.ExceptionType
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toExceptionType() = when (this) {
        is SocketTimeoutException -> ExceptionType.SocketTimeoutException
        is javax.net.ssl.SSLHandshakeException -> ExceptionType.SSLHandshakeException
        is UnknownHostException -> ExceptionType.UnknownHostException
        is java.net.ProtocolException -> ExceptionType.ProtocolException
        is javax.net.ssl.SSLException -> ExceptionType.SSLException
        is java.net.SocketException -> ExceptionType.SocketException
        is java.io.EOFException -> ExceptionType.EOFException
        is java.util.concurrent.CancellationException -> ExceptionType.UserCancellationException
        else -> ExceptionType.GenericException(this.localizedMessage)
    }
