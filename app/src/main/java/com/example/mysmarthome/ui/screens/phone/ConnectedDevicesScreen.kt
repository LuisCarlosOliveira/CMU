package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
import com.example.mysmarthome.ui.components.*

@Composable
fun ConnectedDevicesScreen(navController: NavController) {

   val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val divisionsViewModel: DivisionsViewModel = viewModel(LocalContext.current as MainActivity)
    val divisions = divisionsViewModel.allDivisions.observeAsState()
    var division by rememberSaveable {
        mutableStateOf("")
    }
    var divisionNames: Array<String> = arrayOf("Todos")
    var divisionIds = mutableMapOf<String, String>()
    val devicesViewModel: DevicesViewModel = viewModel(LocalContext.current as MainActivity)
    var devicesDWD: State<DivisionWithDevices?>? = null
    var devicesD: State<List<Device>?>? = null

    if (divisions.value != null) {
        divisions.value!!.forEach {
            divisionNames += it.name
            divisionIds[it.name] = it.idDivision.toString()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBack(
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
                Column(Modifier.padding(5.dp)) {
                    division = DropDownMenu(
                        arrayOf(*divisionNames),
                        stringResource(id = R.string.selectedOptionDivision)
                    )
                }

            }
            val divisionId = divisionIds[division]
            if (divisionId != null) {
                devicesDWD = devicesViewModel.getConectedDevicesByDivision(divisionId.toInt())
                    .observeAsState()

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, bottom = 15.dp)
                ) {

                    Spacer(modifier = Modifier.height(50.dp))
                    var device: List<Device> = emptyList()
                    if (devicesDWD!!.value != null) {
                        device = devicesDWD!!.value!!.devices
                    }
                    LazyColumn {
                        items(device.size) { l ->
                            ListRowWithIcon(
                                title = device.get(l).nome,
                                icon = Icons.Rounded.Delete,
                                action = { }
                            )
                        }
                    }
                }
            } else {
                devicesD = devicesViewModel.getConnectedDevices().observeAsState()
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, bottom = 15.dp)
                ) {

                    Spacer(modifier = Modifier.height(50.dp))
                    var device: List<Device> = emptyList()
                    if (devicesD!!.value != null) {
                        device = devicesD!!.value!!
                    }
                    LazyColumn {
                        items(device.size) { l ->
                            ListRowWithIcon(
                                title = device.get(l).nome,
                                icon = Icons.Rounded.Delete,
                                action = { }
                            )
                        }
                    }
                }
            }
        },

        floatingActionButton = {
            FloatingButton(
                icon = Icons.Rounded.Add,
                title = stringResource(id = R.string.addConnectedDevice),
                action = { navController.navigate("NewDeviceScreen") }
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ConnectedDevicesScreenPreview() {
    ConnectedDevicesScreen(navController = NavController(LocalContext.current))
}
