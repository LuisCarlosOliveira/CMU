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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ConsumptionsScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val park = LatLng(40.888277915243826, -8.485999037385369)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(park, 15f)
    }

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(start = 5.dp),
                    )
                }

                Text(
                    fontWeight = FontWeight.Medium,
                    letterSpacing = letterSpacing,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                    fontSize = 22.sp,
                    text = stringResource(id = R.string.consumptionsTitle)
                )
            }

            Divider(
                startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                    .padding(top = 70.dp)
                    .width(screenWidth - 20.dp)
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
                        text = "Dispositivos que:  "
                    )
                    dropDownMenuConsumptions()
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(400.dp)
                ) {

                    Image(
                        painterResource(id = R.drawable.consumos),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        },
    )
}

@Composable
fun dropDownMenuConsumptions() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions: Array<String> = arrayOf("Todos", "Mais Consomem", "Menos Consomem")
    var selectedText by remember { mutableStateOf(suggestions[0]) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = selectedText,
                maxLines = 2,
                letterSpacing = 1.sp
            )
            Icon(icon, "contentDescription",
                Modifier
                    .clickable { expanded = !expanded })
            Box(modifier = Modifier.padding(top = 15.dp)) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
        }
    }
}


@Preview()
@Composable
fun tentarVer() {
    ConsumptionsScreen(navController = NavController(LocalContext.current))
}