package ie.setu.EventPlanner.ui.screens.map

@Composable
fun MapScreen() {
    val uiSettings by remember { mutableStateOf(MapUiSettings(
        //myLocationButtonEnabled = true,
        compassEnabled = true,
        mapToolbarEnabled = true
    )) }

    val properties by remember {
        mutableStateOf(MapProperties(
            mapType = MapType.NORMAL,
            //isMyLocationEnabled = true
        ))
    }

    val currentLocation = LatLng(52.245696, -7.139102)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
    }

    LaunchedEffect(currentLocation){
        cameraPositionState.animate(CameraUpdateFactory.newLatLng(currentLocation))
        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
    }

    Column{
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            properties = properties
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "SETU",
                snippet = "This is SETU"
            )

        }
    }}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    DonationXTheme {
        MapScreen( )
    }
}
