package com.example.mysmarthome.ui.screens.phone

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.DropDownMenu
import com.example.mysmarthome.ui.components.TopBarBack
import com.google.android.gms.location.*

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@SuppressLint("UnrememberedMutableState", "MissingPermission")
@Composable
fun LocationScreen(navController: NavController/*, location: Location?*/) {
    val locationStatusLat = remember { mutableStateOf("") }
    val locationStatusLon = remember { mutableStateOf("") }
    val permission_given = remember {
        mutableStateOf(0)
    }
    val ctx = LocalContext.current
    if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED  ){
        permission_given.value =2
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it) {
            permission_given.value += 1
        }
    }
    LaunchedEffect(key1 = "Permission") {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
    if (permission_given.value==2) {



        DisposableEffect(Unit) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx)
            fusedLocationClient.lastLocation.addOnSuccessListener {
                locationStatusLat.value = "${it.latitude}"
                locationStatusLon.value = "${it.longitude}"
            }.addOnFailureListener {
                locationStatusLat.value = "41.36751"
                locationStatusLon.value = "-8.1964"
            }

            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                10000,
            ).setMinUpdateIntervalMillis(30000).build();

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locations: LocationResult) {
                    for (location in locations.locations) {
                        locationStatusLat.value = "${location.latitude}"
                        locationStatusLon.value = "${location.longitude}"
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            onDispose { fusedLocationClient.removeLocationUpdates(locationCallback) }

        }
    }

    var park = LatLng(41.36751, -8.1964)

        if(locationStatusLat.value.isNotEmpty() && locationStatusLon.value.isNotEmpty()) {
            park = LatLng(locationStatusLat.value.toDouble(), locationStatusLon.value.toDouble())
        }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(park, 15f)
    }

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))


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


                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(400.dp)
                ) {

                    GoogleMap(
                        cameraPositionState = cameraPositionState
                    ) {
                        Circle(
                            center = park,
                            fillColor = Color.Black,
                            radius = 100.0
                        )
                        /*
                        Marker(
                            state = MarkerState(position = park),
                            title = "Park",
                            snippet = "Marker in Park"
                        )
                         */
                    }
                }
            }
        },
    )
}
