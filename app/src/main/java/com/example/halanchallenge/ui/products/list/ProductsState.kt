package com.example.halanchallenge.ui.products.list

import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.utils.base.State

val InitialProductsState = ProductsState(null , null , null , null)

data class ProductsState(
    val isLoading: Boolean?,
    val error: Throwable?,
    val productsResponse: ProductResponse?,
    val isLogOut: Boolean?
) : State
