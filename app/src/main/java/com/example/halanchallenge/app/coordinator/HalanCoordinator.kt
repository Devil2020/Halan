package com.example.halanchallenge.app.coordinator

import androidx.navigation.NavHostController
import kotlinx.coroutines.delay


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
            popUpTo(Directions.SplashDirection.name) {
                inclusive = closeCurrent
            }
        }
    }

    suspend fun navigateAfter(
        time: Long, direction: Directions,
        closeCurrent: Boolean = false,
        restoringState: Boolean = false,
        launchingSingleTop: Boolean = false
    ) {
        delay(time)
        navigate(direction, closeCurrent, restoringState, launchingSingleTop)
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