package com.example.halanchallenge.app.coordinator

import androidx.navigation.NavHostController


class HalanCoordinator private constructor(private val navHostController: NavHostController) {

    private lateinit var lastDirection: Directions
    fun getNavController() = navHostController
    fun navigate(
        direction: Directions,
        closeCurrent: Boolean = false,
        restoringState: Boolean = false,
        launchingSingleTop: Boolean = false,
    ) = navHostController.navigate(direction.name) {
        restoreState = restoringState
        launchSingleTop = launchingSingleTop
        if (closeCurrent) {
            popUpTo(direction.name) {
                inclusive = true
            }
        }
    }

    fun back() {
        navHostController.popBackStack()
    }

    companion object {
        private val LOCK = Any()
        private var instance: HalanCoordinator? = null
        fun builder(navHostController: NavHostController) = synchronized(LOCK) {
            instance ?: HalanCoordinator(navHostController).also { instance = it }
        }
    }
}