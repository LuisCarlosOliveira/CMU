package com.example.mysmarthome.ui.screens.phone

import LoginPage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun UnconnectedDevicesScreen() {

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
                    modifier = Modifier.padding(top = 7.dp, start = 5.dp),
                    fontSize = 20.sp,
                    text = "Dispositivos Não Conectados"
                )
            }

            Divider(
                startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                    .padding(top = 70.dp)
                    .width(screenWidth - 20.dp)
            )

        },
        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {

                // Em vez de items(x) mete-se foreach ...
                LazyColumn {

                    items(15) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                                .border(
                                    border =
                                    BorderStroke(width = 1.dp, Color.LightGray)
                                )
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.Serif,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 15.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = "Dispositivo"
                            )

                            CheckBoxDemo()

                        }
                    }
                }
            }


        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = {
                    Icon(
                        Icons.Rounded.Check, "",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                },
                text = { Text("Confirmar", fontSize = 18.sp) },
                backgroundColor = Color.Gray,
                onClick = { },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )

        },
        floatingActionButtonPosition = FabPosition.Center,

    )
}

@Composable
fun CheckBoxDemo() {
    val checkedState = remember { mutableStateOf(false) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Preview(showBackground = true)
@Composable
fun UnconnectedDevicesScreenPreview(){
    UnconnectedDevicesScreen()
}