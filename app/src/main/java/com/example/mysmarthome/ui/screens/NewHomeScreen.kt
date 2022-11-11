package com.example.mysmarthome.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewHomeScreen(/* navController: NavController */) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        var letterSpacing by remember {
            mutableStateOf(1.sp)
        }

        var nome by rememberSaveable {
            mutableStateOf("")
        }

        var street by rememberSaveable {
            mutableStateOf("")
        }

        var postalcode by rememberSaveable {
            mutableStateOf("")
        }

        var city by rememberSaveable {
            mutableStateOf("")
        }

        var country by rememberSaveable {
            mutableStateOf("")
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .width(50.dp),
                        onClick = {}) {
                        Icon(
                            Icons.Rounded.ArrowBack, "",
                            tint = Color.Black,
                        )
                    }
                    Text(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = letterSpacing,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                        fontSize = 22.sp,
                        text = "Nova Casa"
                    )
                }

                Divider(
                    startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                        .padding(top = 70.dp)
                        .width(screenWidth - 20.dp)
                )

            },
            content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Nome"
                    )

                    TextField(colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                        disabledIndicatorColor = Color.Blue
                    ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        value = nome,
                        shape = RoundedCornerShape(7.dp),
                        onValueChange = { nome = it },
                        placeholder = { Text(text = "Insira o Nome da Casa") },
                        label = { Text(text = "Nome da Casa") })

                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Morada"
                    )

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .background(Color.LightGray)
                    ) {

                        TextField(colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            disabledIndicatorColor = Color.Blue
                        ),
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            value = street,
                            shape = RoundedCornerShape(7.dp),
                            onValueChange = { street = it },
                            placeholder = { Text(text = "Insira a Rua") },
                            label = { Text(text = "Rua") })

                        TextField(colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            disabledIndicatorColor = Color.Blue
                        ),
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            value = postalcode,
                            shape = RoundedCornerShape(7.dp),
                            onValueChange = { postalcode = it },
                            placeholder = { Text(text = "Insira o Código Postal") },
                            label = { Text(text = "Código Postal") })

                        TextField(colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            disabledIndicatorColor = Color.Blue
                        ),
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            value = city,
                            shape = RoundedCornerShape(7.dp),
                            onValueChange = { city = it },
                            placeholder = { Text(text = "Insira a Cidade") },
                            label = { Text(text = "Cidade") })


                        TextField(colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            disabledIndicatorColor = Color.Blue
                        ),
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            value = country,
                            shape = RoundedCornerShape(7.dp),
                            onValueChange = { country = it },
                            placeholder = { Text(text = "Insira o País") },
                            label = { Text(text = "País") })

                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                            border = BorderStroke(1.dp, Color.Blue),
                            modifier = Modifier
                                .padding(start = 130.dp, top = 20.dp, bottom = 20.dp),
                            shape = RoundedCornerShape(20.dp),
                            onClick = {}) {
                            Text(fontWeight = FontWeight.Medium,text = "Continuar")
                            Icon(
                                Icons.Rounded.ArrowForward, "",
                                tint = Color.Black,
                            )
                        }
                    }
                }
            },
        )
    }
}