package com.example.mysmarthome.ui.screens

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewAccountScreen(/*navController: NavController*/) {
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
        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisibility by rememberSaveable {
            mutableStateOf(false)
        }
        var password2 by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisibility2 by rememberSaveable {
            mutableStateOf(false)
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
                        fontSize = 24.sp,
                        text = "Criar Conta"
                    )

                }
                Divider(
                    startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                        .padding(top = 80.dp)
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
                        placeholder = { Text(text = "Insira o Nome") },
                        label = { Text(text = "Nome") })

                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Email"
                    )

                    TextField(colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Blue,
                        disabledIndicatorColor = Color.Blue
                    ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        value = email,
                        shape = RoundedCornerShape(7.dp),
                        onValueChange = { email = it },
                        placeholder = { Text(text = "Insira o Email") },
                        label = { Text(text = "Email") })

                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Password"
                    )

                    val icon = if (passwordVisibility)
                        painterResource(id = R.drawable.ic_partial_secure)
                    else
                        painterResource(id = R.drawable.ic_secure)

                    TextField(
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            disabledIndicatorColor = Color.Blue
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        value = password,
                        onValueChange = { password = it },
                        shape = RoundedCornerShape(7.dp),
                        placeholder = { Text(text = "Password") },
                        label = { Text(text = "Nova Password") },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                                Icon(painter = icon, contentDescription = "")
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                    )

                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Repetir Password"
                    )

                    val icon2 = if (passwordVisibility2)
                        painterResource(id = R.drawable.ic_partial_secure)
                    else
                        painterResource(id = R.drawable.ic_secure)

                    TextField(
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Blue,
                            disabledIndicatorColor = Color.Blue
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        value = password2,
                        onValueChange = { password2 = it },
                        shape = RoundedCornerShape(7.dp),
                        placeholder = { Text(text = "Repetir Nova Password") },
                        label = { Text(text = "Repetir Password") },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisibility2 = !passwordVisibility2 }) {
                                Icon(painter = icon2, contentDescription = "")
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (passwordVisibility2) VisualTransformation.None else PasswordVisualTransformation()
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        border = BorderStroke(1.dp, Color.Blue),
                        modifier = Modifier
                            .padding(start = 130.dp, top = 20.dp, bottom = 20.dp),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {}) {
                        Text(fontWeight = FontWeight.Medium, text = "Continuar")
                        Icon(
                            Icons.Rounded.ArrowForward, "",
                            tint = Color.Black,
                        )
                    }
                }
            }
        )
    }
}