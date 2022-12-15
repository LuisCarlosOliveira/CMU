package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.ui.components.DropDownMenuOutlined
import com.example.mysmarthome.ui.components.FormNumberTextField
import com.example.mysmarthome.ui.components.FormStringTextField

@Composable
fun NewDeviceScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var name by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf("")
    }
    var port by remember {
        mutableStateOf("")
    }
    var division by remember {
        mutableStateOf("")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {

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
                    modifier = Modifier.padding(top = 7.dp, start = 10.dp, end = 10.dp),
                    fontSize = 22.sp,
                    text = "Inserir Dispositivo"
                )

                IconButton(
                    onClick = {
                        navController.navigate("ConnectedDevicesScreen")
                    }
                ) {
                    Icon(
                        Icons.Filled.Save, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 5.dp),
                    )
                }
            }
            Divider(
                startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                    .padding(top = 70.dp)
                    .width(screenWidth - 20.dp)
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
                    division = DropDownMenuOutlined(
                        modifier1 = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                        modifier2 = Modifier.padding(top = 50.dp),
                        arrayOf("Cozinha", "Sala", "Quarto", "WC", "Garagem")
                    )
                }
            }
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(60.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("HomePageScreen")
                    }
                ) {
                    Icon(
                        Icons.Rounded.Home, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 10.dp)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("ConnectedDevicesScreen")
                    }
                ) {
                    Icon(
                        Icons.Rounded.Devices, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 10.dp)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("ConsumptionsScreen")
                    }
                ) {
                    Icon(
                        Icons.Rounded.Timeline, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 10.dp)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("DefinitionsScreen")
                    }
                ) {
                    Icon(
                        Icons.Rounded.Settings, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
        }
    )
}