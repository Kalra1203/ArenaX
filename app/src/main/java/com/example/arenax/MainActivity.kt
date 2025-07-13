package com.example.arenax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.arenax.ui.theme.ArenaXTheme
import com.example.arenax.ui.theme.BottomDest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArenaXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArenaXTheme {
        Greeting("Android")
    }
}
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomDest.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomDest.Home.route)    { HomeScreen() }
            composable(BottomDest.Explore.route) { ExploreScreen() }
            composable(BottomDest.Profile.route) { ProfileScreen() }
        }
    }
}
@Composable
fun BottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        BottomDest.all.forEach { dest ->
            NavigationBarItem(
                selected = currentRoute == dest.route,
                onClick  = {
                    if (currentRoute != dest.route) {   // avoid multiple copies
                        navController.navigate(dest.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon  = { Icon(dest.icon, dest.label) },
                label = { Text(dest.label) }
            )
        }
    }
}

@Composable fun HomeScreen()    {Text("Home Screen") }
@Composable fun ExploreScreen() {Text("Explore Screen")}
@Composable fun ProfileScreen() {Text("Profile Screen") }

