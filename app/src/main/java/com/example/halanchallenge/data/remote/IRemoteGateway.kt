package com.example.halanchallenge.data.remote

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductResponse

interface IRemoteGateway {

    suspend fun login (loginRequest: LoginRequest) : LoginResponse

    suspend fun getProducts () : ProductResponse

}