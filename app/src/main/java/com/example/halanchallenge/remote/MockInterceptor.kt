package com.example.halanchallenge.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.startsWith("https://data.fixer.io/api/symbols") -> LOGIN_RESPONSE
            uri.startsWith("https://data.fixer.io/api/latest") -> LOGIN_RESPONSE
            uri.startsWith("https://data.fixer.io/api/convert") -> LOGIN_RESPONSE
            uri.startsWith("https://data.fixer.io/api/timeseries") -> LOGIN_RESPONSE
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(responseString.toByteArray()
                .toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    val LOGIN_RESPONSE = ""
    val PRODUCTS_RESPONSE = ""
    val DETAILS_RESPONSE =""

}