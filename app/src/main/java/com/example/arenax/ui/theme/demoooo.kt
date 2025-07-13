//package com.example.arenax.ui.theme
//
//@Composable
//fun EventCard(
//    modifier: Modifier = Modifier,
//    image: Painter = painterResource(R.drawable.sample_banner),   // replace with your banner
//    onShare: () -> Unit = {},
//    onRsvp: () -> Unit = {}
//) {
//    Card(
//        modifier = modifier
//            .width(260.dp)          // tweak to taste
//            .wrapContentHeight(),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column {
//            /* ─── Banner ────────────────────────── */
//            Image(
//                painter = image,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(110.dp),
//                contentScale = ContentScale.Crop
//            )
//
//            /* ─── Main body ─────────────────────── */
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 12.dp)
//            ) {
//
//                /*  Date block  */
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                        .width(40.dp)
//                ) {
//                    Text(
//                        text = "9",
//                        style = MaterialTheme.typography.titleMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        text = "JAN",
//                        style = MaterialTheme.typography.labelSmall,
//                        letterSpacing = 1.sp
//                    )
//                }
//
//                Spacer(Modifier.width(12.dp))
//
//                /*  Details  */
//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    Text(
//                        text = "Small Business Meetup",
//                        style = MaterialTheme.typography.titleMedium,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                    Text(
//                        text = "Jan 9, 2035 • 7:30 PM",
//                        style = MaterialTheme.typography.bodySmall,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                    Text(
//                        text = "Mountain View",
//                        style = MaterialTheme.typography.bodySmall,
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//            }
//
//            /* ─── Divider + actions ─────────────── */
//            Divider()
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(44.dp)
//            ) {
//
//                // Share
//                TextButton(
//                    modifier = Modifier.weight(1f),
//                    onClick = onShare,
//                    shape = RectangleShape      // flush corners with card edges
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Share,
//                        contentDescription = null,
//                        modifier = Modifier.size(18.dp)
//                    )
//                    Spacer(Modifier.width(4.dp))
//                    Text("Share")
//                }
//
//                // Vertical divider between buttons
//                Box(
//                    modifier = Modifier
//                        .width(1.dp)
//                        .fillMaxHeight()
//                        .background(MaterialTheme.colorScheme.outlineVariant)
//                )
//
//                // RSVP
//                TextButton(
//                    modifier = Modifier.weight(1f),
//                    onClick = onRsvp,
//                    shape = RectangleShape
//                ) {
//                    Text("RSVP")
//                }
//            }
//        }
//    }
//}
