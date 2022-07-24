package com.example.halanchallenge.ui.base

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.halanchallenge.app.coordinator.Directions
import com.example.halanchallenge.app.coordinator.HalanCoordinator
import com.example.halanchallenge.ui.auth.LoginScreen
import com.example.halanchallenge.ui.products.detail.ProductDetailsScreen
import com.example.halanchallenge.ui.products.list.ProductListScreen
import com.example.halanchallenge.ui.splash.SplashScreen

@Composable
fun HalanNavHost(coordinator: HalanCoordinator) {
    NavHost(
        navController = coordinator.getNavController(),
        startDestination = Directions.SplashDirection.name
    ) {
        composable(Directions.SplashDirection.name) {
            SplashScreen(coordinator)
        }
        composable(Directions.AuthenticationDirection.name) {
            LoginScreen(coordinator)
        }
        composable(Directions.ProductsDirection.name) {
            ProductListScreen(coordinator)
        }
        composable(Directions.ProductDetailsDirection.name) {
            ProductDetailsScreen(coordinator)
        }
    }
}