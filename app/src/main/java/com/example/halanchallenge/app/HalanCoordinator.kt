package com.example.halanchallenge.app

import android.app.Activity
import android.content.Intent
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.ui.auth.LogInActivity
import com.example.halanchallenge.ui.products.detail.ProductDetailActivity
import com.example.halanchallenge.ui.products.list.ProductsActivity
import com.example.halanchallenge.utils.base.Constants

sealed class HalanDirections {

    data class Auth(val current: Activity) : HalanDirections()

    data class ProductsList(val current: Activity) : HalanDirections()

    data class ProductDetail(val current: Activity , val product: ProductResponse.Product) : HalanDirections()

}

object HalanCoordinator {

    fun navigate(direction: HalanDirections) = when (direction) {

        is HalanDirections.Auth -> {
            navigateToAuth(direction.current)
        }

        is HalanDirections.ProductsList -> {
            navigateToProductsList(direction.current)
        }

        is HalanDirections.ProductDetail -> {
            navigateToProductDetail(direction.current , direction.product)
        }

    }

    private fun navigateToAuth(current: Activity) {
        current.finish()
        current.startActivity(Intent(current, LogInActivity::class.java))

    }

    private fun navigateToProductsList(current: Activity) {
        current.finish()
        current.startActivity(Intent(current, ProductsActivity::class.java))
    }

    private fun navigateToProductDetail(current: Activity , product: ProductResponse.Product) {
        current.startActivity(Intent(current, ProductDetailActivity::class.java).apply { putExtra(Constants.PRODUCT_DETAIL , product) })
    }

}