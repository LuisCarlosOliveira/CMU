package com.example.mysmarthome.ui.screens.tablet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun DefinitionsScreen() {

    val nocturnMode = remember { mutableStateOf(true) }
    val savingEnergyMode = remember { mutableStateOf(true) }
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
                    text = stringResource(id = R.string.definitionsTitle)
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
                    .padding(top = 50.dp)
            ) {

                val definitions: Array<String> = stringArrayResource(id = R.array.definitions)

                definitions.forEach { definition ->

                    if (definition.equals("Modo Noturno")) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
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
                                text = definition
                            )
                            Switch(
                                checked = nocturnMode.value,
                                onCheckedChange = { nocturnMode.value = it },
                                modifier = Modifier.padding(end = 20.dp)
                            )
                        }

                    } else if (definition.equals("Modo Poupar Energia")) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(border = BorderStroke(width = 1.dp, Color.LightGray)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = definition
                            )
                            Switch(
                                checked = savingEnergyMode.value,
                                onCheckedChange = { savingEnergyMode.value = it },
                                modifier = Modifier.padding(end = 20.dp)
                            )

                        }

                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(border = BorderStroke(width = 1.dp, Color.LightGray))
                                .clickable(
                                    onClick = {

                                    },
                                ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = definition
                            )
                        }
                    }
                }
            }
        },
    )
}