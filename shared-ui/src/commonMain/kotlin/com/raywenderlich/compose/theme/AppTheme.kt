package com.example.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content:
    @Composable () -> Unit,
) {
// TODO: Add Colors
    val colors = if (darkTheme) {
        darkColors()
            .copy(
                primary = primaryDarkColor,
                primaryVariant = primaryLightColor,
                secondary = secondaryDarkColor,
                secondaryVariant = secondaryLightColor,
                onPrimary = Color.White,
            )
    } else {
        lightColors()
            .copy(
                primary = primaryColor,
                primaryVariant = primaryLightColor,
                secondary = secondaryColor,
                secondaryVariant = secondaryLightColor,
                onPrimary = Color.Black,
            )
    }
// TODO: Add Theme
    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}