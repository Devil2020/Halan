package com.example.halanchallenge.ui.products.list

import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.ui.auth.LoginState
import com.example.halanchallenge.utils.base.State

val InitialProductsState = ProductsState(null , null , null , null)
val LoadingState = ProductsState(isLoading = true)
val EmptyState = ProductsState (isLoading = null , productsResponse = ProductResponse(products = emptyList() , status = "200"))
fun Throwable.toProductErrorState () = ProductsState(
    isLoading = null ,
    productsResponse = null ,
    error = this
)

data class ProductsState(
    val isLoading: Boolean? = null,
    val error: Throwable?= null,
    val productsResponse: ProductResponse?= null,
    val isLogOut: Boolean?= null
) : State
