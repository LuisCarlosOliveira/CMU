package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.relations.device_division.DivisionWithDevices
import com.example.mysmarthome.database.view_models.DevicesViewModel
import com.example.mysmarthome.database.view_models.DivisionsViewModel
import com.example.mysmarthome.ui.components.*

@Composable
fun DivisionDetailsScreen(navController: NavController, idDivision: Int) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        val divisionsViewModel: DivisionsViewModel = viewModel(LocalContext.current as MainActivity)
        divisionsViewModel.getOneDivision(idDivision)

        val devicesViewModel: DevicesViewModel = viewModel(LocalContext.current as MainActivity)
        val division = divisionsViewModel.division.observeAsState()

        var divisionWithDevices: State<DivisionWithDevices?>? = null
        if (division.value != null) {
            divisionWithDevices =
                devicesViewModel.getConectedDevicesByDivision(division.value!!.idDivision)
                    .observeAsState()
            var devices: List<Device> = emptyList()
            if (divisionWithDevices.value != null) {
                devices = divisionWithDevices.value!!.devices
            }
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

            val deviceType: Array<String> = stringArrayResource(id = R.array.deviceType)
            var estoros: Array<String> = arrayOf("")
            var luzes: Array<String> = arrayOf("")
            var tomadas: Array<String> = arrayOf("")
            var devicesIds = mutableMapOf<String, String>()

            devices.forEach {
                if (it.type.equals("Estoros")) {
                    estoros += it.nome
                    devicesIds[it.nome] = it.idDevice.toString()
                } else if (it.type.equals("Luzes")) {
                    luzes += it.nome
                    devicesIds[it.nome] = it.idDevice.toString()
                } else {
                    tomadas += it.nome
                    devicesIds[it.nome] = it.idDevice.toString()
                }

            }

            var letterSpacing by remember {
                mutableStateOf(1.sp)
            }

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopBarBackForward(
                        title = division.value!!.name,
                        actionBtns = {
                            IconButton(onClick = { }) {
                                Icon(Icons.Rounded.Edit, "", tint = Color.Black)
                            }

                            IconButton(onClick = {
                                divisionsViewModel.removeDivision(division.value!!)
                                navController.navigate("HomePageScreen")}) {
                                Icon(Icons.Rounded.Delete, "", tint = Color.Black)
                            }
                        },
                        navController = navController
                    )
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
                                    .padding(start = 7.dp, end = 7.dp),
                                devicesIds
                            )
                        }
                    }
                },
                floatingActionButton = {
                    FloatingButton(
                        icon= Icons.Rounded.Add,
                        title = stringResource(id = R.string.addConnectedDevice),
                        action = { navController.navigate("NewDeviceScreen") }
                    )
                }
            )
        }
    }
}

@Preview()
@Composable
fun PreviewDivisionDetailsScreen() {
    DivisionDetailsScreen(
        navController = NavController(LocalContext.current), 1
    )
}