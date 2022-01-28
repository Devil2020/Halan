package com.example.halanchallenge.remote

import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitGateway {

    @POST(BuildConfig.AUTH_URL)
    suspend fun login(@Body auth: LoginRequest): LoginResponse

    @GET(BuildConfig.PRODUCTS_URL)
    suspend fun getProductsList(@Header("Authorization") token: String): ProductResponse

}