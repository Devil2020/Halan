package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow

abstract class IProductsGateway : UseCase() {
    abstract fun executeGetProductsUseCase(token: String): Flow<ProductResponse>
}

class ProductsGateway(private val repository: IProductRepository) : IProductsGateway() {
    override fun executeGetProductsUseCase(token: String) =
        executeUseCase { repository.loadProductsList(ProductRequest(token)) }

}