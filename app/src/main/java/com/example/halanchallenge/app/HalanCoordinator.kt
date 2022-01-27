package com.example.halanchallenge.app

import android.content.Context
import android.content.Intent
import com.example.halanchallenge.ui.auth.LogInActivity

sealed class HalanDirections {

    data class Auth (val context: Context) : HalanDirections()

    data class ProductsList (val context: Context) : HalanDirections()

    data class ProductDetail (val context: Context) : HalanDirections()

}

object HalanCoordinator {

    fun navigate (direction : HalanDirections) = when (direction){

        is HalanDirections.Auth -> {
            navigateToAuth (direction.context)
        }

        is HalanDirections.ProductsList -> {
            navigateToProductsList(direction.context)
        }

        is HalanDirections.ProductDetail -> {
            navigateToProductDetail(direction.context)
        }

    }

    private fun navigateToAuth (context: Context){
        context.startActivity(Intent(context , LogInActivity::class.java))
    }

    private fun navigateToProductsList (context: Context){

    }

    private fun navigateToProductDetail (context: Context){

    }

}