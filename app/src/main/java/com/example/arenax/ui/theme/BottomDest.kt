package com.example.arenax.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomDest(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home     : BottomDest("home",     Icons.Outlined.Home,       "Home")
    object Explore  : BottomDest("explore",  Icons.Outlined.Search,     "Explore")
    object Profile  : BottomDest("profile",  Icons.Outlined.Person,     "Profile")

    companion object { val all = listOf(Home, Explore, Profile) }
}
