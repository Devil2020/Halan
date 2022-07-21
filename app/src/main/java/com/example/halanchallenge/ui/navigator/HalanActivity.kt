package com.example.halanchallenge.ui.navigator

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import com.example.halanchallenge.ui.splash.SplashScreen
import com.example.halanchallenge.ui.theme.DarkBlue

class HalanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            val scaffoldState = rememberScaffoldState()
            Scaffold(scaffoldState = scaffoldState) {
                SplashScreen()
            }
        }
    }

}
