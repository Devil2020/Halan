package com.example.halanchallenge.data.repository

import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.domain.repository.IProductRepository

class ProductRepository (private val remoteGateway: IRemoteGateway) : IProductRepository {

    override suspend fun loadProductsList(productsRequest: ProductRequest): ProductResponse {
        return remoteGateway.getProducts(productsRequest)
    }
}