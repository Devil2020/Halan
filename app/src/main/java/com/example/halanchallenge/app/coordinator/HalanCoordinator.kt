package com.example.halanchallenge.app.coordinator

import androidx.navigation.NavHostController


class HalanCoordinator private constructor(private val navHostController: NavHostController) {

    private lateinit var lastDirection: Directions
    fun getNavController() = navHostController
    fun navigate(direction: Directions) {}
    fun back() {

    }

    companion object {
        private val LOCK = Any()
        private var instance: HalanCoordinator? = null
        fun builder(navHostController: NavHostController) = synchronized(LOCK) {
            instance ?: HalanCoordinator(navHostController).also { instance = it }
        }
    }
}