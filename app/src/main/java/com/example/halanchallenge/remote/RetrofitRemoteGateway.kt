package com.example.halanchallenge.remote

import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductResponse

class RetrofitRemoteGateway(
    private val gateWay: RetrofitGateway = RetrofitCore.getGatewayAgent()
) : IRemoteGateway {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return gateWay.login(loginRequest)
    }

    override suspend fun getProducts(): ProductResponse {
        return gateWay.getProductsList()
    }

}