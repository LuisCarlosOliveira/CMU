package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.AddImage
import com.example.mysmarthome.ui.components.DropDownMenu
import com.example.mysmarthome.ui.components.TopbarBack
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ConsumptionsScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }
    mainActivity.notification_member_request()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopbarBack(
                title = stringResource(id = R.string.consumptionsTitle),
                navController = navController
            )
        },
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = letterSpacing,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 17.sp,
                        text = stringResource(id = R.string.devicesThat)
                    )
                    Column(
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(6.dp)) {
                        DropDownMenu(
                            stringArrayResource(id = R.array.dropdownConsumptions), stringResource(
                                id = R.string.optionSelectedConsumption
                            )
                        )
                    }
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(400.dp)
                ) {
                    AddImage(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.consumos)
                    )
                }
            }
        },
    )
}

@Preview()
@Composable
fun Consumptions() {
    ConsumptionsScreen(navController = NavController(LocalContext.current))
}
