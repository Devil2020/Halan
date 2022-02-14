package com.example.halanchallenge.app

import android.app.Activity
import android.content.Intent
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.ui.auth.LogInActivity
import com.example.halanchallenge.ui.products.detail.ProductDetailActivity
import com.example.halanchallenge.ui.products.list.ProductsActivity
import com.example.halanchallenge.utils.base.Constants

interface Direction {
    fun add()
    fun delete()
}

interface Coordinator {
    fun navigate(direction: Direction)
    fun back()
}

class AuthanticationDirection(private val current: Activity) : Direction {
    override fun add() {
        current.finish()
        current.startActivity(Intent(current, LogInActivity::class.java))
    }

    override fun delete() {
        current.finish()
    }
}

class ProductListDirection(private val current: Activity) : Direction {
    override fun add() {
        current.finish()
        current.startActivity(Intent(current, ProductsActivity::class.java))
    }

    override fun delete() {
        current.finish()
    }
}

class ProductDetailDirection(
    private val current: Activity,
    private val product: ProductResponse.Product
) : Direction {
    override fun add() {
        current.startActivity(Intent(current, ProductDetailActivity::class.java).apply {
            putExtra(
                Constants.PRODUCT_DETAIL,
                product
            )
        })
    }

    override fun delete() {
        current.finish()
    }
}


object HalanCoordinator : Coordinator {
    private lateinit var lastDirection: Direction
    override fun navigate(direction: Direction) = direction.add().also { lastDirection = direction }
    override fun back() = lastDirection.delete()
}