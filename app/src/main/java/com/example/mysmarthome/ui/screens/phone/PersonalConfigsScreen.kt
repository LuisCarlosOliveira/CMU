package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.ListRowWithIcon
import com.example.mysmarthome.ui.components.TopbarBack

@Composable
fun PersonalConfigsScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            TopbarBack(
                title = stringResource(id = R.string.personalConfigs),
                navController = navController
            )
        },

        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 15.dp)
            ) {

                LazyColumn {
                    items(15) {
                        ListRowWithIcon(
                            title = stringResource(id = R.string.personalConfigsRowTitle),
                            icon = Icons.Rounded.Edit,
                            action = { navController.navigate("DefinitionsDivisionsDevicesScreen") })
                    }
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PersonalConfigsScreenPreview() {
    PersonalConfigsScreen(navController = NavController(LocalContext.current))
}