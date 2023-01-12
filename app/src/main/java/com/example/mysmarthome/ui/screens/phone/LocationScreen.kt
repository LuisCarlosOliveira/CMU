package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.DropDownMenu
import com.example.mysmarthome.ui.components.TopBarBack
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val park = LatLng(40.888277915243826, -8.485999037385369)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(park, 15f)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBack(
                title = stringResource(id = R.string.locationTitle),
                navController = navController
            )
        },
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropDownMenu(
                        options = stringArrayResource(id = R.array.memberLocation),
                        optionSelected = stringResource(
                            id = R.string.selectOneChildMember
                        )
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(400.dp)
                ) {

                    GoogleMap(
                        cameraPositionState = cameraPositionState
                    ) {
                        Marker(
                            position = park,
                            title = "Park",
                            snippet = "Marker in Park"
                        )
                    }
                }
            }
        },
    )
}

@Preview()
@Composable
fun PreviewLocationScreen() {
    LocationScreen(navController = NavController(LocalContext.current))
}