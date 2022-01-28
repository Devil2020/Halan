package com.example.halanchallenge.domain.repository

import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.entities.product.ProductResponse

interface IProductRepository {

    suspend fun loadProductsList(productsRequest: ProductRequest): ProductResponse

}