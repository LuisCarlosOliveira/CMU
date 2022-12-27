package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.NormalButton
import com.example.mysmarthome.ui.components.TopBarBack

@Composable
fun AssociateHouseScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBack(
                    title = stringResource(id = R.string.add_home),
                    navController = navController
                )
            },
            content = {
                Column(
                    Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    NormalButton(modifier = Modifier
                        .width(250.dp)
                        .height(80.dp),
                        action = {
                            navController.navigate("QrCodeScanner" )
                        },
                        title = stringResource(id = R.string.readQRCode)
                    )
                }
            }
        )
    }
}

@Preview()
@Composable
fun PreviewAssociateHomeScreen() {
    AssociateHouseScreen(navController = NavController(LocalContext.current))
}