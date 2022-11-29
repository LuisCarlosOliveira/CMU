package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.mysmarthome.R

@Composable
fun ProfileScreen() {
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
                            text = "João " + stringResource(id = R.string.admin)
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
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 20.dp)
                    ) {
                        val options: Array<String> = stringArrayResource(id = R.array.profile)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(
                                    border =
                                    BorderStroke(width = 1.dp, Color.LightGray)
                                ), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = options[0]
                            )
                            IconButton(
                                onClick = { }//dialogOpen = true }
                            ) {
                            Icon(
                                Icons.Rounded.Email, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(end = 15.dp),
                            )
                        }}
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(
                                    border =
                                    BorderStroke(width = 1.dp, Color.LightGray)
                                ), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = options[1]
                            )
                            Icon(
                                Icons.Rounded.Password, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(end = 15.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(
                                    border =
                                    BorderStroke(width = 1.dp, Color.LightGray)
                                ), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = options[2]
                            )
                            Icon(
                                Icons.Rounded.PriorityHigh, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(end = 15.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(
                                    border =
                                    BorderStroke(width = 1.dp, Color.LightGray)
                                ), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = options[3]
                            )
                            Icon(
                                Icons.Rounded.Star, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(end = 15.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(
                                    border =
                                    BorderStroke(width = 1.dp, Color.LightGray)
                                ), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = options[4]
                            )
                            Icon(
                                Icons.Rounded.Group, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(end = 15.dp),
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun MyAlertDialog() {
    var dialogOpen by remember { mutableStateOf(false) }
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = { dialogOpen = false },
            buttons = {
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { dialogOpen = false }) {
                        Text(text = "Confirm")
                    }
                    Button(onClick = { dialogOpen = false }) {
                        Text(text = "Dismiss")
                    }
                }
            },
            title = { Text(text = "Alterar Email") },
            text = { Text(text = "Pequeno formulario") },
            modifier = Modifier.fillMaxWidth().padding(28.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true ) )
    }
    Button(onClick = { dialogOpen = true }) {
        Text(modifier = Modifier.fillMaxWidth(), text = "AlertDialog", textAlign = TextAlign.Center)
    }
}


