package com.example.arenax.ui.theme.presentation

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arenax.R

@Composable
fun HomeScreen() {
    val listState = rememberLazyListState()
    var isHeaderVisible by remember { mutableStateOf(true) }

    var previousIndex by remember { mutableStateOf(0) }
    var previousOffset by remember { mutableStateOf(0) }

    // Detect scroll direction
    LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
        val currIndex = listState.firstVisibleItemIndex
        val currOffset = listState.firstVisibleItemScrollOffset

        isHeaderVisible = when {
            currIndex < previousIndex -> true  // scrolling up
            currIndex > previousIndex -> false // scrolling down
            currOffset < previousOffset -> true
            currOffset > previousOffset -> false
            else -> isHeaderVisible
        }

        previousIndex = currIndex
        previousOffset = currOffset
    }

    val animatedHeight by animateDpAsState(
        targetValue = if (isHeaderVisible) Dp.Unspecified else 0.dp, label = "headerHeight"
    )

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .height(animatedHeight)
                .fillMaxWidth()
        ) {
            if (isHeaderVisible || animatedHeight != 0.dp) {
                HeaderCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        TournamentCard(modifier = Modifier.weight(1f), listState = listState)
    }
}

@Composable
fun HeaderCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.hacker_img),
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.imgg),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "PLAYERS ZONE",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Ultimate Gaming Hub", fontSize = 14.sp, color = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "General Info",
                        color = Color.Blue,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            // Handle link click
                        })

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(onClick = { /* Handle Invite */ }) {
                            Text("Invite")
                        }
                        Button(onClick = { /* Handle Chat */ }) {
                            Text("Chat with Us")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TournamentCard(modifier: Modifier = Modifier, listState: LazyListState) {
    val context = LocalContext.current
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(15) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Jan 9 2013 | 4:30 PM")
                            Text(text = "Mountain View")
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                            ) {
                                TextButton(
                                    modifier = Modifier.weight(1f), onClick = {
                                        Toast.makeText(
                                            context, "Share Button Clicked", Toast.LENGTH_SHORT
                                        ).show()
                                    }, shape = RectangleShape
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = "Share")
                                }

                                TextButton(
                                    modifier = Modifier.weight(1f), onClick = {
                                        Toast.makeText(
                                            context, "Create Button Clicked", Toast.LENGTH_SHORT
                                        ).show()
                                    }, shape = RectangleShape
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Create,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = "Create")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
