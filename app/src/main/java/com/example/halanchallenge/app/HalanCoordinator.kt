package com.example.halanchallenge.app

import android.app.Activity
import android.content.Intent
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.utils.base.Constants

interface Direction {
    fun add()
    fun delete()
}

interface Coordinator {
    fun navigate(direction: Direction)
    fun back()
}

object HalanCoordinator : Coordinator {
    private lateinit var lastDirection: Direction
    override fun navigate(direction: Direction) = direction.add().also { lastDirection = direction }
    override fun back() = lastDirection.delete()
}