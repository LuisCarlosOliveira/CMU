package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.DevicesViewModel
import com.example.mysmarthome.ui.components.*

@Composable
fun LightScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var ison by remember {
        mutableStateOf(false)
    }

    var duration by remember {
        mutableStateOf("")
    }

    var turn by remember {
        mutableStateOf("")
    }

    var ssid by remember {
        mutableStateOf("")
    }

    var mode by remember {
        mutableStateOf("")
    }

    var onoffSelected by remember {
        mutableStateOf(false)
    }

    var modeSelected by remember {
        mutableStateOf(false)
    }

    var cor = mutableListOf<Int>(0, 0, 0, 0)

    var hasTimer by rememberSaveable {
        mutableStateOf(false)
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

    var sliderIntensity by remember { mutableStateOf("") }

    var sliderBrilho by remember { mutableStateOf("") }

    var temp by remember {
        mutableStateOf("")
    }
    var overtemp by remember {
        mutableStateOf(false)
    }

    var dialogInfo by remember { mutableStateOf(false) }

    var dialogSelectColor by remember { mutableStateOf(false) }

    var saveBtnClicked by remember { mutableStateOf(false) }

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    val lightsViewModel: DevicesViewModel = viewModel()

    val lightContent = lightsViewModel.getLight.observeAsState()

    val lightActionsColorModeContent = lightsViewModel.getLightActionsColorMode.observeAsState()

    lightsViewModel.getLight()

    if (lightContent.value != null) {
        ssid = lightContent.value?.wifi_sta!!.ssid
        lightContent.value?.lights?.forEach {
            ison = it.ison
            if (ison) {
                turn = "on"
            } else {
                turn = "off"
            }
            mode = it.mode
            cor.removeAll { true }
            cor.add(0, it.red)
            cor.add(1, it.green)
            cor.add(2, it.blue)
            cor.add(3, it.white)
            sliderIntensity = it.gain.toString()
            sliderBrilho = it.brightness.toString()
            hasTimer = it.has_timer
            beginTimer = it.timer_started.toString()
            remainingTimer = it.timer_remaining.toString()
            duration = it.timer_duration.toString()
        }
        temp = lightContent.value?.temperature.toString()
        overtemp = lightContent.value?.overtemperature!!
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBackForward(
                title = stringResource(id = R.string.nameDevice),
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
                AlertPopup(
                    state = dialogInfo,
                    btn1Text = "Ok",
                    btn2Text = "Cancelar",
                    content = {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            PersonalText(Color.Red, null, "Início Temporizador: ")
                            PersonalText(Color.Black, null, beginTimer)

                            PersonalText(Color.Red, null, "Fim Temporizador: ")
                            PersonalText(
                                Color.Black,
                                null,
                                (beginTimer + duration)
                                //(beginTimer.toFloat() + durationTimer.toFloat()).toString()
                            )

                            PersonalText(Color.Red, null, "Tempo Restante: ")
                            PersonalText(Color.Black, null, remainingTimer)
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
                                text = stringResource(id = R.string.modeOfLight)
                            )
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = stringResource(id = R.string.colorOfLight)
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (mode.equals("color")) {
                                PersonalText(
                                    color = Color.Red,
                                    modifier = Modifier
                                        .padding(top = 7.dp)
                                        .width(screenWidth / 2),
                                    text = "Intensidade:"
                                )
                            } else {
                                PersonalText(
                                    color = Color.Red,
                                    modifier = Modifier
                                        .padding(top = 7.dp)
                                        .width(screenWidth / 2),
                                    text = "Brilho:"
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
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.height(80.dp)
                        ) {
                            if (!modeSelected) {
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = letterSpacing,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 17.sp,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                    modifier = Modifier.clickable(onClick = {
                                        modeSelected = true
                                    }), text = mode,
                                    style = TextStyle(textDecoration = TextDecoration.Underline)
                                )
                            } else {
                                mode = DropDownMenuOutlined(
                                    modifier1 = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 20.dp),
                                    modifier2 = Modifier.padding(top = 50.dp),
                                    arrayOf("color", "white")
                                )
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (mode.equals("color")) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .height(35.dp)
                                        .width(35.dp)
                                        .border(BorderStroke(1.dp, Color.Black))
                                        .clickable(onClick = { dialogSelectColor = true })
                                        .background(Color(cor[0], cor[1], cor[2], cor[3]))
                                ) { }
                                if (dialogSelectColor) {
                                    AlertDialog(
                                        onDismissRequest = { dialogSelectColor = false },
                                        buttons = {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(bottom = 5.dp),
                                                horizontalArrangement = Arrangement.SpaceEvenly
                                            ) {
                                                Button(colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color.Blue
                                                ),
                                                    onClick = {
                                                        dialogSelectColor = false
                                                    }) {
                                                    Text(color = Color.White, text = "Selecionar")
                                                }
                                                Button(colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color.Blue
                                                ),
                                                    onClick = { dialogSelectColor = false }) {
                                                    Text(color = Color.White, text = "Cancelar")
                                                }
                                            }
                                        },

                                        title = { },

                                        text = {
                                            Column(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight()
                                            ) {
                                                cor = ColorPicker(navController = navController)
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(400.dp)
                                            .padding(2.dp),
                                        shape = RoundedCornerShape(10.dp),
                                        backgroundColor = Color.White,
                                        properties = DialogProperties(
                                            dismissOnBackPress = true,
                                            dismissOnClickOutside = true
                                        )
                                    )
                                }
                            } else {
                                cor.removeAll { true }
                                cor.add(0, 255)
                                cor.add(1, 255)
                                cor.add(2, 255)
                                cor.add(3, 255)
                                Box(
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .height(35.dp)
                                        .width(35.dp)
                                        .border(BorderStroke(1.dp, Color.Black))
                                        .background(Color(255, 255, 255, 255))
                                ) { }
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            if (mode.equals("color")) {
                                Column {
                                    Text(text = ((sliderIntensity.toFloat()) * 100).toString())
                                    sliderIntensity = SliderInput(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 20.dp)
                                    )
                                }
                            } else {
                                Column {
                                    //Text(text = ((sliderBrilho.toFloat()) * 100).toString())
                                    sliderBrilho = SliderInput(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 20.dp)
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = temp
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

                        if (saveBtnClicked) {
/*
                            if (turn != "toggle") {
                                if (durationTimer.isNotEmpty())
                                    lightsViewModel.getLightActionsColorMode(
                                        turn,
                                        mode,
                                        cor,
                                        durationTimer.toInt()
                                    ) else {
                                    lightsViewModel.getLightActionsColorMode(
                                        turn,
                                        mode,
                                        cor,
                                        null
                                    )
                                }
                            } else {
                                if (ison) {
                                    lightsViewModel.getLightTimer(
                                        turn,
                                        durationTimer.toInt()
                                    )
                                } else {
                                    lightsViewModel.getLightTimer(
                                        turn,
                                        null
                                    )
                                }
                            }
*/

                            when (turn) {
                                "on" ->
                                    if (mode.equals("color")) {
                                        if (durationTimer.isNotEmpty()) {
                                            lightsViewModel.getLightActionsColorMode(
                                                turn,
                                                mode,
                                                cor,
                                                sliderIntensity.toFloat(),
                                                durationTimer.toInt()
                                            )
                                        } else {
                                            lightsViewModel.getLightActionsColorMode(
                                                turn,
                                                mode,
                                                cor,
                                                sliderIntensity.toFloat(),
                                                null
                                            )
                                        }
                                    } else {
                                        if (durationTimer.isNotEmpty()) {
                                            lightsViewModel.getLightActionsWhiteMode(
                                                turn,
                                                mode,
                                                cor,
                                                sliderBrilho.toFloat(),
                                                durationTimer.toInt()
                                            )
                                        } else {
                                            lightsViewModel.getLightActionsWhiteMode(
                                                turn,
                                                mode,
                                                cor,
                                                sliderBrilho.toFloat(),
                                                null
                                            )
                                        }
                                    }

                                "off" ->
                                    if (durationTimer.isNotEmpty()) {
                                        lightsViewModel.getLightTimer("off", durationTimer.toInt())
                                    } else {
                                        lightsViewModel.getLightTimer("off", null)
                                    }

                                "toggle" ->
                                    if (ison) {
                                        if (durationTimer.isNotEmpty()) {
                                            lightsViewModel.getLightTimer(
                                                "on",
                                                durationTimer.toInt()
                                            )
                                        } else {
                                            lightsViewModel.getLightTimer("on", null)
                                        }
                                    } else {
                                        lightsViewModel.getLightTimer("off", null)
                                    }
                            }
                        }
                    }
                }
            }
        },
    )
}

@Preview()
@Composable
fun PreviewDeviceScreen() {
    LightScreen(navController = NavController(LocalContext.current))
}