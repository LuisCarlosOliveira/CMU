package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Save
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.retrofit.helper.RetrofitHelper
import com.example.mysmarthome.retrofit.shelly_api.plug.PlugAPI
import com.example.mysmarthome.ui.components.*

@Composable
fun PlugScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var ssid by remember {
        mutableStateOf("")
    }
    var ison by remember {
        mutableStateOf(false)
    }
    var hasTimer by rememberSaveable {
        mutableStateOf(false)
    }
    var beginTimer by rememberSaveable {
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

    val api = RetrofitHelper.getInstance(3000).create(PlugAPI::class.java)

    var dialogInfo by remember { mutableStateOf(false) }
    var dialogOpen by remember { mutableStateOf(false) }

    var letterSpacing by remember {
        mutableStateOf(1.sp)
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
                },
                navController = navController
            )
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
                    .padding(start = 20.dp, top = 40.dp, bottom = 20.dp)
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
                        if (dialogOpen) {
                            AlertDialog(
                                onDismissRequest = { dialogOpen = false },
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
                                                dialogOpen = false
                                                hasTimer = true
                                            }) {
                                            Text(color = Color.White, text = "Guardar")
                                        }
                                        Button(colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Blue
                                        ),
                                            onClick = { dialogOpen = false }) {
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

                                        TextField(
                                            colors = TextFieldDefaults.textFieldColors(
                                                focusedIndicatorColor = Color.Blue,
                                                unfocusedIndicatorColor = Color.Blue,
                                                disabledIndicatorColor = Color.Blue
                                            ),
                                            modifier = Modifier
                                                .padding(top = 10.dp)
                                                .fillMaxWidth(),
                                            value = beginTimer,
                                            shape = RoundedCornerShape(7.dp),
                                            onValueChange = { beginTimer = it },
                                            placeholder = { Text(text = "Início Temporizador") },
                                            label = { Text(text = "Início") },
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number
                                            )
                                        )

                                        TextField(
                                            colors = TextFieldDefaults.textFieldColors(
                                                focusedIndicatorColor = Color.Blue,
                                                unfocusedIndicatorColor = Color.Blue,
                                                disabledIndicatorColor = Color.Blue
                                            ),
                                            modifier = Modifier
                                                .padding(top = 30.dp, bottom = 10.dp)
                                                .fillMaxWidth(),
                                            value = durationTimer,
                                            shape = RoundedCornerShape(7.dp),
                                            onValueChange = { durationTimer = it },
                                            placeholder = { Text(text = "Duração Temporizador") },
                                            label = { Text(text = "Duração") },
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number
                                            )
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(2.dp),
                                shape = RoundedCornerShape(10.dp),
                                backgroundColor = Color.White,
                                properties = DialogProperties(
                                    dismissOnBackPress = true,
                                    dismissOnClickOutside = true
                                )
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
                                text = stringResource(id = R.string.overPowerDevice)
                            )
                        }
                        NormalButton(
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp),
                            action = { dialogOpen = true },
                            title = "Temporizador"
                        )

                        Spacer(Modifier.padding(10.dp))
                    }

                    /*
                    api.getPlugRelay().enqueue(object : Callback<Plug> {
                        override fun onResponse(
                            call: Call<Plug>,
                            response: Response<Plug>
                        ) {
                            var pp = response.body()!!
                            println("VAMOS VER   " + pp)
                            ssid = pp.wifi_sta.ssid
                            ison = pp.lights[0].ison
                            temp = pp.temperature.toString()
                            overtemp = pp.overtemperature.toString()
                            ssid = pp.wifi_sta.ssid

                        }

                        override fun onFailure(call: Call<Plug>, t: Throwable) {

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
                                text = "20ºC"
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = overtemp.toString()
                            )
                        }
                        Spacer(Modifier.padding(10.dp))
                        Row(Modifier.height(80.dp)) {
                            PersonalText(
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp),
                                text = overPower.toString()
                            )
                        }
                        NormalButton(
                            modifier = Modifier
                                .width(160.dp)
                                .height(50.dp),
                            action = { },
                            title = "Guardar"
                        )
                    }
                }
            }
        },
    )
}

@Preview()
@Composable
fun PreviewPlugScreen() {
    PlugScreen(navController = NavController(LocalContext.current))
}