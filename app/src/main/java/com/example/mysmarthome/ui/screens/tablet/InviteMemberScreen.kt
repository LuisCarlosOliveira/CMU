package com.example.mysmarthome.ui.screens.tablet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun InviteMemberScreen() {

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
                    IconButton(
                        onClick = { }
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
                        text = stringResource(id = R.string.inviteMemberTitle)
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
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Rounded.QrCode2, "",
                            modifier = Modifier
                                .size(380.dp)
                                .padding(top = 50.dp),
                            tint = Color.Black,
                        )
                    }

                    Column() {
                        Text(
                            modifier = Modifier.padding(start = 25.dp, top = 50.dp),
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
                                .padding(start = 20.dp, top = 30.dp, end = 20.dp)
                                .fillMaxWidth(),
                            value = email,
                            shape = RoundedCornerShape(7.dp),
                            onValueChange = { email = it },
                            placeholder = { Text(text = "Insira o Email") },
                            label = { Text(text = "Email") })
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                            border = BorderStroke(1.dp, Color.Blue),
                            modifier = Modifier
                                .padding(top = 40.dp, bottom = 20.dp)
                                .width(150.dp)
                                .height(50.dp),
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