package com.example.halanchallenge.remote

import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.entities.product.ProductResponse

class RetrofitRemoteGateway(
    private val gateWay: RetrofitGateway
) : IRemoteGateway {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return gateWay.login(loginRequest)
    }

    override suspend fun getProducts(productRequest: ProductRequest): ProductResponse {
        return gateWay.getProductsList(productRequest.token)
    }

}