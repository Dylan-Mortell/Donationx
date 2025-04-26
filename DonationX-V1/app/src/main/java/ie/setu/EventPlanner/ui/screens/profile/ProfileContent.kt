package ie.setu.EventPlanner.ui.screens.profile


@Composable
fun ProfileContent(
    photoUri: Uri?,
    displayName: String,
    email: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUri)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = Crop,
            modifier = Modifier.clip(CircleShape).width(180.dp).height(180.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = displayName,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = email,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}
