package com.example.mysmarthome.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.MainActivity

@Composable
fun InviteMemberScreen(mainActivity: MainActivity /*,navController: NavController*/) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp

            var email by rememberSaveable {
                mutableStateOf("")
            }

            var letterSpacing by remember {
                mutableStateOf(1.sp)
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
                            text = "Convidar Membro"
                        )
                    }

                    Divider(
                        startIndent = 20.dp,
                        thickness = 1.dp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 70.dp)
                            .width(screenWidth - 20.dp)
                    )

                },
                content = {
                    Column() {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(id = mainActivity.imgQRCode()),
                                contentDescription = "",
                                modifier = Modifier.size(380.dp)
                            )
                        }
                        Column() {
                            Text(
                                modifier = Modifier.padding(start = 25.dp, top = 20.dp),
                                letterSpacing = letterSpacing,
                                fontWeight = FontWeight.Bold,
                                text = "Email:"
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

                            Button(
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                                border = BorderStroke(1.dp, Color.Blue),
                                modifier = Modifier
                                    .padding(start = 130.dp, top = 20.dp, bottom = 20.dp),
                                shape = RoundedCornerShape(20.dp),
                                onClick = {}) {
                                Text(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    text = "Confirmar"
                                )
                                Icon(
                                    Icons.Rounded.Check, "",
                                    tint = Color.Black,
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}