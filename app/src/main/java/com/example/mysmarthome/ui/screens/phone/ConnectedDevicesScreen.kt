package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.DropDownMenu
import com.example.mysmarthome.ui.components.FloatingButton
import com.example.mysmarthome.ui.components.ListRowWithIcon
import com.example.mysmarthome.ui.components.TopbarBack

@Composable
fun ConnectedDevicesScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopbarBack(
                title = stringResource(id = R.string.connectedDevicesTitle),
                navController = navController
            )
        },

        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    text = stringResource(R.string.filterByDivision),
                    maxLines = 2,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp)

                )
                DropDownMenu(
                    stringArrayResource(id = R.array.home_divisions),
                    stringResource(id = R.string.selectedOptionDivision)
                )
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 15.dp)
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                LazyColumn {
                    items(15) {
                        ListRowWithIcon(
                            title = stringResource(id = R.string.titleRow),
                            icon = Icons.Rounded.Delete,
                            action = { }
                        )
                    }
                }
            }
        },

        floatingActionButton = {
            FloatingButton(
                icon= Icons.Rounded.Add,
                title = stringResource(id = R.string.addConnectedDevice),
                action = { navController.navigate("UnconnectedDevicesScreen") }
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ConnectedDevicesScreenPreview() {
    ConnectedDevicesScreen(navController = NavController(LocalContext.current))
}