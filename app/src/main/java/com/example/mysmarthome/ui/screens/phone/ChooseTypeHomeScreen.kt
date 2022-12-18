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
import com.example.mysmarthome.ui.components.ChooseButton
import com.example.mysmarthome.ui.components.TopbarBack

@Composable
fun ChooseTypeHomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopbarBack(
                        title = stringResource(id = R.string.chooseHomeTitle),
                        navController = navController
                    )
                },
                content = {
                    Column(
                        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ChooseButton(
                            title = stringResource(id = R.string.newHomeTitle),
                            action = { navController.navigate("NewHomeScreen") })

                        Spacer(Modifier.padding(60.dp))

                        ChooseButton(
                            title = stringResource(id = R.string.add_home),
                            action = { navController.navigate("AssociateHouseScreen") })
                    }
                },
            )
        }
    }
}

@Preview()
@Composable
fun PreviewChooseTypeHomeScreen() {
    ChooseTypeHomeScreen(navController= NavController(LocalContext.current))
}