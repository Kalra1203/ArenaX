package com.example.arenax.ui.theme.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomDest(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {
    object Home : BottomDest("home", Icons.Filled.Home, Icons.Outlined.Home, "Home")
    object Explore : BottomDest("explore", Icons.Filled.Search, Icons.Outlined.Search, "Explore")
    object Profile : BottomDest("profile", Icons.Filled.Person, Icons.Outlined.Person, "Profile")

    companion object {
        val all = listOf(Home, Explore, Profile)
    }
}
