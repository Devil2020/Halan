package com.example.halanchallenge.ui.launcher

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.halanchallenge.app.coordinator.HalanCoordinator
import com.example.halanchallenge.ui.base.HalanNavHost
import com.example.halanchallenge.ui.splash.SplashScreen
import com.example.halanchallenge.ui.theme.HalanTheme

class HalanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            val scaffoldState = rememberScaffoldState()
            val controller = rememberNavController()
            val coordinator = remember {
                HalanCoordinator.builder(controller)
            }
            Scaffold(scaffoldState = scaffoldState) {
                HalanTheme(darkTheme = false) {
                    HalanNavHost(coordinator)
                }
            }
        }
    }

}
