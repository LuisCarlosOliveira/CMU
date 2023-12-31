package com.example.mysmarthome.ui.screens.phone

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.view_models.DevicesViewModel
import com.example.mysmarthome.database.view_models.DivisionsViewModel
import com.example.mysmarthome.database.view_models.HomesViewModel
import com.example.mysmarthome.ui.components.*

@Composable
fun NewDeviceScreen(navController: NavController) {
    val divisionsViewModel: DivisionsViewModel = viewModel(LocalContext.current as MainActivity)
    divisionsViewModel.getDivisions()
    val divisions = divisionsViewModel.allDivisions.observeAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val homesViewModel: HomesViewModel = viewModel(LocalContext.current as MainActivity)
    val home = homesViewModel.home.observeAsState()
    LaunchedEffect(Unit ){ homesViewModel.getFirstHome()}
    val devicesViewModel: DevicesViewModel = viewModel(LocalContext.current as MainActivity)

    var name by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf("")
    }
    var port by remember {
        mutableStateOf("")
    }
    var divisionN by remember {
        mutableStateOf("")
    }
    var divisionNames: Array<String> = arrayOf("")
    var divisionIds = mutableMapOf<String, String>()

    if (divisions.value != null) {
        divisions.value!!.forEach {
            divisionNames += it.name
            divisionIds[it.name] = it.idDivision.toString()
        }
    } else {
        divisionNames = arrayOf("")
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBackForward(
                title = stringResource(id = R.string.newDeviceTitle),
                actionBtns = {
                    IconButton(onClick = {
                        if (divisionN != null) {
                            val divisionId = divisionIds[divisionN]
                            if (divisionId != null) {
                                Log.d("DIVISAOOO", divisionId)
                                devicesViewModel.insertDevice(
                                    Device(
                                        divisionId.toInt(),
                                        port.toInt(),
                                        name,
                                        type
                                    ), divisionId.toInt(), home.value!!
                                )
                                navController.navigate("HomePageScreen")
                            } else {
                                Log.d("DIVISAOOO", "Division ID not found")
                            }
                        } else {
                            Log.d("DIVISAOOO", "Division not selected")
                        }
                    })
                    {
                        Icon(Icons.Rounded.Save, "", tint = Color.Black)
                    }
                },
                navController = navController
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                name = FormStringTextField("Nome", "Nome Dispositivo", "Nome")


                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Tipo"
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    type = DropDownMenuOutlined(
                        modifier1 = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                        modifier2 = Modifier.padding(top = 50.dp),
                        arrayOf("Estoros", "Luzes", "Tomadas")
                    )
                }

                port = FormNumberTextField("Porta", "Porta Dispositivo", "Porta")

                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
                    fontWeight = FontWeight.Bold,
                    text = "Divisão"
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    divisionN = DropDownMenuOutlined(
                        modifier1 = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                        modifier2 = Modifier.padding(top = 50.dp),
                        arrayOf(*divisionNames)
                    )

                }

            }
        },
        bottomBar = {
            BottombarWithHome(navController = navController)
        }
    )
}

@Preview()
@Composable
fun PreviewNewDeviceScreen() {
    NewDeviceScreen(navController = NavController(LocalContext.current))
}