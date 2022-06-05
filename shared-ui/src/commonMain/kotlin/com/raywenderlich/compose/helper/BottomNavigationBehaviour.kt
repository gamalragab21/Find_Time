package com.example.compose.helper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        Screen.TimeZonesScreen.title,
        Icons.Filled.Language,
        "Timezones"
    ),
    BottomNavigationItem(
        Screen.FindTimeScreen.title,
        Icons.Filled.Place,
        "Find Time"
    )
)