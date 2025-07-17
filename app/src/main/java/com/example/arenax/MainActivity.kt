package com.example.arenax

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.arenax.ui.theme.ArenaXTheme
import com.example.arenax.ui.theme.data.BottomDest
import com.example.arenax.ui.theme.presentation.*
import com.example.arenax.ui.theme.presentation.common.BottomBar
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("809416266580-bo3ui0hvjm1nsq8ce33a85spnjcb74v8.apps.googleusercontent.com") // Your Web Client ID
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            ArenaXTheme {
                val navController = rememberNavController()
                val auth = FirebaseAuth.getInstance()
                var user by remember { mutableStateOf(auth.currentUser) }

                // Observe auth state and redirect to login if logged out
                LaunchedEffect(auth.currentUser) {
                    user = auth.currentUser
                    if (user == null) {
                        navController.navigate("login") {
                            popUpTo(0)
                        }
                    }
                }

                // Google Sign-In Launcher
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val account = task.getResult(ApiException::class.java)
                        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                        auth.signInWithCredential(credential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val current = navController.currentBackStackEntry?.destination?.route
                                    if (current == "register") {
                                        navController.navigate("login") {
                                            popUpTo("register") { inclusive = true }
                                        }
                                    } else {
                                        navController.navigate(BottomDest.Home.route) {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    }
                                } else {
                                    Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } catch (e: ApiException) {
                        Toast.makeText(this, "Google Sign-In Error", Toast.LENGTH_SHORT).show()
                    }
                }

                val hideBottomBarRoutes = listOf("splash", "login", "register")

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val currentRoute = currentRoute(navController)
                        if (currentRoute !in hideBottomBarRoutes) {
                            BottomBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("splash") {
                            SplashScreen(navController)
                        }

                        composable("login") {
                            LoginScreen(navController) {
                                launcher.launch(googleSignInClient.signInIntent)
                            }
                        }

                        composable("register") {
                            RegisterScreen(navController) {
                                launcher.launch(googleSignInClient.signInIntent)
                            }
                        }

                        composable(BottomDest.Home.route) {
                            HomeScreen()
                        }

                        composable(BottomDest.Explore.route) {
                            ExploreScreen()
                        }

                        composable(BottomDest.Profile.route) {
                            ProfileScreen(
                                onLogout = {
                                    FirebaseAuth.getInstance().signOut()
                                    googleSignInClient.signOut()
                                    navController.navigate("login") {
                                        popUpTo(0)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
