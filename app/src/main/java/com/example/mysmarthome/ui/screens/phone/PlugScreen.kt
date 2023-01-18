package com.example.mysmarthome.ui.screens.phone

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.DevicesViewModel
import com.example.mysmarthome.retrofit.helper.RetrofitHelper
import com.example.mysmarthome.retrofit.shelly_api.plug.PlugAPI
import com.example.mysmarthome.ui.components.*
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlugScreen(navController: NavController, id: Int) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val devicesViewModel: DevicesViewModel = viewModel(LocalContext.current as MainActivity)
    val device = devicesViewModel.getOneDevice(id).observeAsState()
    var ssid by remember {
        mutableStateOf("")
    }
    var ison by remember {
        mutableStateOf(false)
    }

    var onoffSelected by remember {
        mutableStateOf(false)
    }

    var turn by remember {
        mutableStateOf("")
    }

    var duration by remember {
        mutableStateOf("")
    }

    var hasTimer by rememberSaveable {
        mutableStateOf(true)
    }
    var beginTimer by rememberSaveable {
        mutableStateOf("")
    }
    var remainingTimer by rememberSaveable {
        mutableStateOf("")
    }
    var durationTimer by rememberSaveable {
        mutableStateOf("")
    }
    var overPower by remember {
        mutableStateOf(false)
    }
    var temp by remember {
        mutableStateOf("")
    }
    var overtemp by remember {
        mutableStateOf(false)
    }

    var beginTimerAtualizado by remember {
        mutableStateOf("")
    }
    var remainingTimerAtualizado by remember {
        mutableStateOf("")
    }
    var durationTimerAtualizado by remember {
        mutableStateOf("")
    }

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var saveBtnClicked by remember { mutableStateOf(false) }

    var dialogInfo by remember { mutableStateOf(false) }

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }
    if(device.value != null) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBackForward(
                    title = device.value!!.nome,
                    actionBtns = {
                        IconButton(onClick = { dialogInfo = true }) {
                            Icon(Icons.Rounded.Info, "", tint = Color.Black)
                        }

    val plugsStatusViewModel: DevicesViewModel = viewModel()

    val plugStatusContent = plugsStatusViewModel.getPlugStatus.observeAsState()

    LaunchedEffect(Unit) {
        plugsStatusViewModel.getPlugStatus()
    }

    if (plugStatusContent.value != null) {
        plugStatusContent.value?.relays?.forEach {
            beginTimerAtualizado = it.timer_started.toString()
            remainingTimerAtualizado = it.timer_remaining.toString()
            durationTimerAtualizado = it.timer_duration.toString()
        }
    }

    val plugsViewModel: DevicesViewModel = viewModel()

    val plugsContent = plugsViewModel.getPlug.observeAsState()

    LaunchedEffect(Unit) {
        plugsViewModel.getPlug()
    }

    if (plugsContent.value != null) {
        ssid = plugsContent.value?.wifi_sta!!.ssid
        plugsContent.value?.relays?.forEach {
            ison = it.is_on
            if (ison) {
                turn = "on"
            } else {
                turn = "off"
            }
            hasTimer = it.has_timer
            beginTimer = it.timer_started.toString()
            duration = it.timer_duration.toString()
            remainingTimer = it.timer_remaining.toString()
            overPower = it.overpower
        }
        temp = plugsContent.value?.temperature.toString()
        overtemp = plugsContent.value?.overtemperature!!
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBackForward(
                title = "Tomada Parede Esquerda",
                actionBtns = {
                    IconButton(onClick = { dialogInfo = true }) {
                        Icon(Icons.Rounded.Info, "", tint = Color.Black)
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

            if (dialogInfo) {
                plugsStatusViewModel.getPlugStatus()

                if (plugStatusContent.value != null) {
                    plugStatusContent.value?.relays?.forEach {
                        beginTimerAtualizado = it.timer_started.toString()
                        remainingTimerAtualizado = it.timer_remaining.toString()
                        durationTimerAtualizado = it.timer_duration.toString()
                    }
                }

                AlertPopup(
                    state = dialogInfo,
                    btn1Text = "Ok",
                    btn2Text = "Cancelar",
                    content = {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val dt = Instant.ofEpochSecond(((beginTimerAtualizado.toLong())))
                                .atZone(ZoneId.of("Portugal"))
                                .toLocalDateTime()
                            PersonalText(Color.Red, null, "Início Temporizador: ")
                            PersonalText(Color.Black, null, dt.toString())

                            PersonalText(Color.Red, null, "Duração Temporizador: ")
                            PersonalText(
                                Color.Black,
                                null,
                                durationTimerAtualizado + " seg"
                            )

                            PersonalText(Color.Red, null, "Tempo Restante: ")
                            PersonalText(Color.Black, null, remainingTimerAtualizado + " seg")
                        }
                    },
                    actionBtn = { dialogInfo = false },
                    actionBtn2 = { dialogInfo = false })
            }
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
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.onOff)
                            )
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.temperatureOfLight)
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.timerOfLight)
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
                                    .width(150.dp)
                                    .height(50.dp),
                                action = { dialogOpen = true },
                                title = "Temporizador"
                            )
                        }
                    }

                    Column() {
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = ssid
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (!onoffSelected) {
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = letterSpacing,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 17.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                    modifier = Modifier.clickable(onClick = {
                                        onoffSelected = true
                                    }), text = turn,
                                    style = TextStyle(textDecoration = TextDecoration.Underline)
                                )
                            } else {
                                turn = DropDownMenuOutlined(
                                    modifier1 = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 20.dp),
                                    modifier2 = Modifier.padding(top = 50.dp),
                                    arrayOf("on", "off", "toggle")
                                )
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = temp + " ºC"
                            )
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            durationTimer = SimpleNumberTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 20.dp),
                                placeholder = "Insira a Duracao",
                                label = duration
                            )
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (overtemp.toString().equals("false")) {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp),
                                    text = "Não"
                                )
                            } else {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp),
                                    text = "Sim"
                                )
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (overPower.toString().equals("false")) {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp),
                                    text = "Não"
                                )
                            } else {
                                PersonalText(
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp),
                                    text = "Sim"
                                )
                            }
                        }
                        if (saveBtnClicked) {
                            when (turn) {
                                "on" ->
                                    if (durationTimer.isNotEmpty()) {
                                        plugsViewModel.getPlugTimer(
                                            turn,
                                            durationTimer.toInt()
                                        )
                                    } else {
                                        plugsViewModel.getPlugTimer(
                                            turn,
                                            null
                                        )
                                    }

                                "off" ->
                                    if (durationTimer.isNotEmpty()) {
                                        plugsViewModel.getPlugTimer(
                                            turn,
                                            durationTimer.toInt()
                                        )
                                    } else {
                                        plugsViewModel.getPlugTimer(
                                            turn,
                                            null
                                        )
                                    }

                                "toggle" ->
                                    if (ison) {
                                        if (durationTimer.isNotEmpty()) {
                                            plugsViewModel.getPlugTimer(
                                                "on",
                                                durationTimer.toInt()
                                            )
                                        } else {
                                            plugsViewModel.getPlugTimer("on", null)
                                        }
                                    } else {
                                        plugsViewModel.getPlugTimer("off", null)
                                    }
                            }
                            saveBtnClicked = false
                            Toast.makeText(
                                LocalContext.current,
                                "Operação " + turn + " executada.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview()
@Composable
fun PreviewPlugScreen() {
    PlugScreen(navController = NavController(LocalContext.current),1)
}