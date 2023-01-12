package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.AddImage
import com.example.mysmarthome.ui.components.TopBarBack

@Composable
fun VirtualPersonalAssistantScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBack(
                    title = stringResource(id = R.string.virtualAssistantTitle),
                    navController = navController
                )
            },
            content = {
                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 200.dp, bottom = 90.dp)
                ) {
                    AddImage(null, painterResource(id = R.drawable.personal_assistant))
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            Icons.Rounded.Mic, "",
                            modifier = Modifier
                                .padding(start = 7.dp)
                                .fillMaxSize(),
                            tint = Color.Black
                        )
                    },
                    onClick = { },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                    modifier = Modifier.size(70.dp),
                    text = { }
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Preview()
@Composable
fun PreviewVirtualPersonalAssistantScreen() {
    VirtualPersonalAssistantScreen(navController = NavController(LocalContext.current))
}