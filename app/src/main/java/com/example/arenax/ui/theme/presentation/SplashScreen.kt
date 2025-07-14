package com.example.arenax.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arenax.R
import com.example.arenax.ui.theme.data.BottomDest
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController, modifier: Modifier = Modifier.fillMaxSize()
) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(BottomDest.Home.route) {
            popUpTo("splash") {
                inclusive = true  // remove splash from backstack
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.free_fire),
                contentDescription = "ArenaX Logo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds

            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("ArenaX", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }


}