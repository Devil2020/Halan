package com.example.halanchallenge.domain.entities.error


import androidx.annotation.Keep

@Keep
data class ErrorResponse(
    val message: String?, // missing username
    val status: String? // BAD_REQUEST
)