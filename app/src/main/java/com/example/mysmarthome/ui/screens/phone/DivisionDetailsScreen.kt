package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
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
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.*

@Composable
fun DivisionDetailsScreen(mainActivity: MainActivity, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        val deviceType: Array<String> = stringArrayResource(id = R.array.deviceType)
        val estoros: Array<String> = stringArrayResource(id = R.array.estorosOptions)
        val luzes: Array<String> = stringArrayResource(id = R.array.luzesOptions)
        val tomadas: Array<String> = stringArrayResource(id = R.array.tomadasOptions)

        var letterSpacing by remember {
            mutableStateOf(1.sp)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                Topbar2EndIcons(content = {
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
                        modifier = Modifier.padding(top = 7.dp, end = 150.dp),
                        fontSize = 22.sp,
                        text = "Garagem"
                    )
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Rounded.Delete, "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(50.dp)
                        )
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Rounded.Edit, "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(50.dp)
                        )
                    }
                })
            },
            content = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp, bottom = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Garage,
                            contentDescription = "",
                            modifier = Modifier.size(150.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 20.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        NormalButton(
                            modifier = Modifier
                                .width(200.dp)
                                .height(40.dp),
                            { },
                            stringResource(id = R.string.editImage)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 40.dp, bottom = 40.dp, start = 7.dp, end = 7.dp)
                    ) {
                        CollapsableLazyColumn(
                            navController, sections = listOf(
                                CollapsableSection(
                                    title = deviceType[0],
                                    rows = estoros,
                                ),
                                CollapsableSection(
                                    title = deviceType[1],
                                    rows = luzes,
                                ),
                                CollapsableSection(
                                    title = deviceType[2],
                                    rows = tomadas,
                                )
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(start = 7.dp, end = 7.dp)
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingButton(
                    icon = Icons.Rounded.Star,
                    title = stringResource(id = R.string.applyPreferences),
                    action = {})
            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Preview()
@Composable
fun PreviewDivisionDetailsScreen() {
    DivisionDetailsScreen(
        mainActivity = MainActivity(),
        navController = NavController(LocalContext.current)
    )
}