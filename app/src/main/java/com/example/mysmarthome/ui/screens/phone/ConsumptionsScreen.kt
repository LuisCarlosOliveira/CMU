package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.DropDownMenu
import com.example.mysmarthome.ui.components.PieChart
import com.example.mysmarthome.ui.components.TopBarBack

@Composable
fun ConsumptionsScreen(mainActivity: MainActivity, navController: NavController) {

    var optionSelected by remember {
        mutableStateOf("")
    }

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    mainActivity.notification_member_request()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            TopBarBack(
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
                        //color = Color.Red,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 17.sp,
                        text = stringResource(id = R.string.devicesThat)
                    )
                    Column(
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(6.dp)
                    ) {
                        optionSelected = DropDownMenu(
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
                ) {
                    PieChart(optionSelected)
                }
            }
        },
    )
}

@Preview()
@Composable
fun ConsumptionsScreen() {
    ConsumptionsScreen(MainActivity(), navController = NavController(LocalContext.current))
}