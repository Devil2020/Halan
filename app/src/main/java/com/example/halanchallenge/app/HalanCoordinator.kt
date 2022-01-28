package com.example.halanchallenge.app

import android.content.Context
import android.content.Intent
import com.example.halanchallenge.ui.auth.LogInActivity
import com.example.halanchallenge.ui.products.detail.ProductDetailActivity
import com.example.halanchallenge.ui.products.list.ProductsActivity

sealed class HalanDirections {

    data class Auth(val context: Context) : HalanDirections()

    data class ProductsList(val context: Context) : HalanDirections()

    data class ProductDetail(val context: Context) : HalanDirections()

}

object HalanCoordinator {

    fun navigate(direction: HalanDirections) = when (direction) {

        is HalanDirections.Auth -> {
            navigateToAuth(direction.context)
        }

        is HalanDirections.ProductsList -> {
            navigateToProductsList(direction.context)
        }

        is HalanDirections.ProductDetail -> {
            navigateToProductDetail(direction.context)
        }

    }

    private fun navigateToAuth(context: Context) {
        context.startActivity(Intent(context, LogInActivity::class.java))
    }

    private fun navigateToProductsList(context: Context) {
        context.startActivity(Intent(context, ProductsActivity::class.java))
    }

    private fun navigateToProductDetail(context: Context) {
        context.startActivity(Intent(context, ProductDetailActivity::class.java))
    }

}