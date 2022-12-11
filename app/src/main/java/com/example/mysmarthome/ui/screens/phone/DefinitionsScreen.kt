package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.R

@Composable
fun DefinitionsScreen(navController: NavController) {

    val nocturnMode = remember { mutableStateOf(true) }
    val savingEnergyMode = remember { mutableStateOf(true) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var dialogOpen by remember { mutableStateOf(false) }

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
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 10.dp)
            ) {
                val definitions: Array<String> = stringArrayResource(id = R.array.definitions)

                definitions.forEach { definition ->

                    if (definition.equals("Modo Noturno")) {
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
                                .height(90.dp)
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

                                            }) {
                                            Text(
                                                color = Color.White,
                                                text = "Sim"
                                            )
                                        }
                                        Button(colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Blue
                                        ),
                                            onClick = { dialogOpen = false }) {
                                            Text(
                                                color = Color.White,
                                                text = "Não"
                                            )
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
                                        Text(
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 20.sp,
                                            color = Color.Black,
                                            text = "Pretende Verdadeiramente Eliminar esta Casa?"
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(2.dp),
                                shape = RoundedCornerShape(10.dp),
                                backgroundColor = Color.White,
                                properties = DialogProperties(
                                    dismissOnBackPress = true,
                                    dismissOnClickOutside = true
                                )
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(border = BorderStroke(width = 1.dp, Color.LightGray))
                                .clickable(
                                    onClick = {
                                        when (definition) {
                                            "Assistente Pessoal" -> navController.navigate("VirtualPersonalAssistantScreen")
                                            "Criar Nova Casa" -> navController.navigate("NewHomeScreen")
                                            "Eliminar Casa" -> dialogOpen = true
                                            "Ajuda" -> navController.navigate("HelpScreen")
                                            "Sobre" -> navController.navigate("AboutScreen")
                                        }
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