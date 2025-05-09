package ie.setu.EventPlanner.ui.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import ie.setu.EventPlanner.ui.theme.EventPlannerTheme

@Composable
fun MapScreen() {
    val uiSettings by remember { mutableStateOf(MapUiSettings(
        //myLocationButtonEnabled = true,
        compassEnabled = true,
        mapToolbarEnabled = true
    )) }

    val properties by remember {
        mutableStateOf(
            MapProperties(
            mapType = MapType.NORMAL,
            //isMyLocationEnabled = true
        )
        )
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
    EventPlannerTheme {
        MapScreen( )
    }
}
