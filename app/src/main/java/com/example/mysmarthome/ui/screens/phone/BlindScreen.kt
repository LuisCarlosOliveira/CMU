package com.example.mysmarthome.ui.screens.phone

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.DevicesViewModel
import com.example.mysmarthome.retrofit.helper.RetrofitHelper
import com.example.mysmarthome.retrofit.shelly_api.blind.BlindAPI
import com.example.mysmarthome.ui.components.*

@Composable
fun BlindScreen(navController: NavController, id: Int) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val devicesViewModel: DevicesViewModel = viewModel(LocalContext.current as MainActivity)
    val device = devicesViewModel.getOneDevice(id).observeAsState()
    var ssid by remember {
        mutableStateOf("")
    }

    var state by remember {
        mutableStateOf("")
    }

    var selected_state by remember {
        mutableStateOf("")
    }

    var choose_state by remember {
        mutableStateOf(false)
    }

    var power by remember {
        mutableStateOf("")
    }

    var rollerOverTemp by remember {
        mutableStateOf(false)
    }

    var stopReason by remember {
        mutableStateOf("")
    }

    var lastDirection by remember {
        mutableStateOf("")
    }

    var currentPos by remember {
        mutableStateOf("")
    }

    var pos by remember {
        mutableStateOf("")
    }

    var temp by remember {
        mutableStateOf("")
    }

    var overtemp by remember {
        mutableStateOf(false)
    }

    var refreshed by remember {
        mutableStateOf(false)
    }

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    var posAtualizada by remember {
        mutableStateOf("")
    }

    var saveBtnClicked by remember { mutableStateOf(false) }

    val blindsViewModel: DevicesViewModel = viewModel()

    val blindContent = blindsViewModel.getBlind.observeAsState()

    var p = blindsViewModel.posit.observeAsState()

    blindsViewModel.getBlind()

    if (blindContent.value != null) {
        ssid = blindContent.value?.wifi_sta!!.ssid
        blindContent.value?.rollers?.forEach {
            state = it.state
            selected_state = it.state
            power = it.power.toString()
            if (p.value != null) {
                pos = p.value.toString()
            }
            rollerOverTemp = it.overtemperature
            stopReason = it.stop_reason
            lastDirection = it.last_direction
        }
        temp = blindContent.value?.temperature.toString()
        overtemp = blindContent.value?.overtemperature!!
    }

    val blindsStatusViewModel: DevicesViewModel = viewModel()

    val blindStatusContent = blindsStatusViewModel.getBlindStatus.observeAsState()

    blindsStatusViewModel.getLightStatus()

    if (blindStatusContent.value != null) {
        blindStatusContent.value?.rollers?.forEach {
            posAtualizada = it.current_pos.toString()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBackForward(
                title = "Estoros Frente",
                actionBtns = {

                    IconButton(onClick = {
                        refreshed = true
                    }) {
                        Icon(Icons.Rounded.Refresh, "", tint = Color.Black)
                    }

                    IconButton(onClick = { navController.navigate("PersonalConfigsScreen") }) {
                        Icon(Icons.Rounded.Star, "", tint = Color.Black)
                    }

                    IconButton(onClick = { saveBtnClicked = true }) {
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
                    .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column() {
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.ssidDevice)
                            )
                        }
                        if (stopReason != "normal") {
                            Spacer(Modifier.padding(10.dp))
                            Row(Modifier.height(80.dp)) {
                                PersonalText(
                                    color = Color.Red,
                                    modifier = Modifier
                                        .padding(top = 7.dp)
                                        .width(screenWidth / 2),
                                    text = "Motivo de Paragem: "
                                )
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.stateDevice)
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = "Última Posição: "
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.powerDevice)
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.overTempDevice)
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.positionBlind)
                            )
                        }

                    Column() {

                        if (refreshed) {
                            blindsStatusViewModel.getBlindStatus()

                            if (blindStatusContent.value != null) {
                                blindStatusContent.value?.rollers?.forEach {
                                    if (p.value != null) {
                                        posAtualizada = p.value.toString()
                                    }
                                }
                            }
                            refreshed = false
                        }


                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp, end = 20.dp),
                                text = ssid
                            )
                        }

                        if (stopReason != "normal") {
                            Spacer(Modifier.padding(10.dp))
                            Row(Modifier.height(80.dp)) {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp, end = 20.dp),
                                    text = stopReason
                                )
                            }
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (!choose_state) {
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = letterSpacing,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 17.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp, end = 20.dp)
                                        .clickable(onClick = {
                                            choose_state = true
                                        }), text = selected_state,
                                    style = TextStyle(textDecoration = TextDecoration.Underline)
                                )
                            } else {
                                selected_state = DropDownMenuOutlined(
                                    modifier1 = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 20.dp),
                                    modifier2 = Modifier.padding(top = 50.dp),
                                    arrayOf("close", "open", "stop", "to_pos")
                                )
                            }
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp, end = 20.dp),
                                text = lastDirection
                            )
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp, end = 20.dp),
                                text = power + " W"
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (rollerOverTemp.toString().equals("false")) {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp, end = 20.dp),
                                    text = "Não"
                                )
                            } else {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp, end = 20.dp),
                                    text = "Sim"
                                )
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (!selected_state.equals("to_pos")) {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp, end = 20.dp),
                                    text = pos
                                )
                            } else {
                                currentPos = SimpleNumberTextField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 20.dp),
                                    placeholder = "Insira a Posição",
                                    label = pos
                                )
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp, end = 20.dp),
                                text = temp + " ºC"
                            )
                        }

                        if (saveBtnClicked) {
                            when (selected_state) {
                                "close" ->
                                    blindsViewModel.getBlindActions("close", null)

                                "open" -> blindsViewModel.getBlindActions("open", null)

                                "stop" -> blindsViewModel.getBlindActions("stop", null)

                                "to_pos" -> {
                                    if (currentPos.isNotEmpty()) {
                                        blindsViewModel.getBlindActions(
                                            "to_pos",
                                            currentPos.toInt()
                                        )
                                    } else {
                                        blindsViewModel.getBlindActions(
                                            "to_pos",
                                            0
                                        )
                                    }
                                }
                            }
                            saveBtnClicked = false
                            Toast.makeText(
                                LocalContext.current,
                                "Operação " + selected_state + " executada.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
        )
    }
}

@Preview()
@Composable
fun PreviewBlindScreen() {
    BlindScreen(navController = NavController(LocalContext.current),1)
}