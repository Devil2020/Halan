package com.example.halanchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = DarkBlue,
    primaryVariant = DarkBlue,
    onPrimary = White,
    secondary = LightGreen,
    secondaryVariant = LightGreen,
    onSecondary = Color.White,
    error = Color.Red,
    onBackground = DarkBlue,
)

private val DarkThemeColors = darkColors(
    primary = DarkBlue,
    primaryVariant = DarkBlue,
    onPrimary = White,
    secondary = LightGreen,
    secondaryVariant = LightGreen,
    onSecondary = Color.White,
    error = Color.Red,
    onBackground = DarkBlue,
)

@Composable
fun HalanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = HalanTypography,
        shapes = HalanShapes,
        content = content
    )
}
