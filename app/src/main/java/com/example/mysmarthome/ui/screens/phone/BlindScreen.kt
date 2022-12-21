package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.retrofit.helper.RetrofitHelper
import com.example.mysmarthome.retrofit.shelly_api.blind.BlindAPI
import com.example.mysmarthome.ui.components.AlertPopup
import com.example.mysmarthome.ui.components.NormalButton
import com.example.mysmarthome.ui.components.PersonalText
import com.example.mysmarthome.ui.components.Topbar2EndIcons

@Composable
fun BlindScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var hasTimer by rememberSaveable {
        mutableStateOf(false)
    }
    var beginTimer by rememberSaveable {
        mutableStateOf("")
    }
    var durationTimer by rememberSaveable {
        mutableStateOf("")
    }
    var dialogInfo by remember { mutableStateOf(false) }
    var dialogOpen by remember { mutableStateOf(false) }

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
                    modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                    fontSize = 22.sp,
                    text = stringResource(id = R.string.nameDevice)
                )

                IconButton(
                    onClick = {
                        dialogInfo = true
                    }
                ) {
                    Icon(
                        Icons.Rounded.Info, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                    )
                }

                IconButton(
                    onClick = {
                        navController.navigate("PersonalConfigsScreen")
                    }
                ) {
                    Icon(
                        Icons.Rounded.Star, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 15.dp),
                    )
                }
            })

            if (hasTimer && !dialogOpen) {
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
                                PersonalText(Color.Black, null, "10:00h ")

                                PersonalText(Color.Red, null, "Fim Temporizador: ")
                                PersonalText(Color.Black, null, "10:30h ")

                                PersonalText(Color.Red, null, "Tempo Restante: ")
                                PersonalText(Color.Black, null, "30 min ")
                            }
                        },
                        actionBtn = { dialogInfo = false },
                        actionBtn2 = { dialogInfo = false })
                }
            } else {
                null
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 40.dp)
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
                                text = "Nome:"
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
                                text = "Posição: "
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .width(screenWidth / 2),
                                text = "Potência: "
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

                    }

                    var temp by remember {
                        mutableStateOf("")
                    }
                    var ison by remember {
                        mutableStateOf(false)
                    }
                    var ssid by remember {
                        mutableStateOf("")
                    }

                    var overtemp by remember {
                        mutableStateOf("")
                    }
                    val api = RetrofitHelper.getInstance(3000).create(BlindAPI::class.java)
/*
                    api.getLBlind().enqueue(object : Callback<Blind> {
                        override fun onResponse(
                            call: Call<Blind>,
                            response: Response<Blind>
                        ) {
                            var pp = response.body()!!
                            println("VAMOS VER   " + pp)
                            ssid = pp.wifi_sta.ssid
                            ison = pp.lights[0].ison
                            mode = pp.lights[0].mode
                            temp = pp.temperature.toString()
                            overtemp = pp.overtemperature.toString()
                            ssid = pp.wifi_sta.ssid

                        }

                        override fun onFailure(call: Call<Blind>, t: Throwable) {

                            println(t.message)
                        }
                    })
*/
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
                            Switch(
                                checked = ison,
                                onCheckedChange = { ison = it },
                                modifier = Modifier
                                    .padding(end = 160.dp)
                                    .width(screenWidth / 2),
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = "20"
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = "200W"
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = "20ºC"
                            )
                        }
                        NormalButton(
                            modifier = Modifier
                                .width(160.dp)
                                .height(50.dp),
                            action = { },
                            title = stringResource(id = R.string.saveLight)
                        )
                    }
                }
            }
        },
    )
}

@Preview()
@Composable
fun PreviewBlindScreen() {
    BlindScreen(navController = NavController(LocalContext.current))
}