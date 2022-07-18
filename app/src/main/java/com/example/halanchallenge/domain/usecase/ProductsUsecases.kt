package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.ui.products.list.EmptyState
import com.example.halanchallenge.ui.products.list.LoadingState
import com.example.halanchallenge.ui.products.list.ProductsState
import com.example.halanchallenge.ui.products.list.toProductErrorState
import kotlinx.coroutines.flow.*

abstract class IProductsGateway : UseCase() {
    abstract fun executeGetProductsUseCase(token: String): Flow<ProductsState>
}

class ProductsGateway(private val repository: IProductRepository) : IProductsGateway() {
    override fun executeGetProductsUseCase(token: String): Flow<ProductsState> {
        return executeUseCase { repository.loadProductsList(ProductRequest(token)) }
            .onStart { LoadingState }
            .map {
                it.toSuccessState()
            }
            .onEmpty { EmptyState }
            .catch {
                it.toProductErrorState()
            }
    }
}