package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.*
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
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.DropDownMenuOutlined

@Composable
fun UnconnectedDevicesScreen(mainActivity: MainActivity, navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var division by remember {
        mutableStateOf("")
    }

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
                    modifier = Modifier.padding(top = 7.dp, start = 5.dp),
                    fontSize = 20.sp,
                    text = "Dispositivos Não Conectados"
                )
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
                                        navController.navigate("HomePageScreen")
                                        mainActivity.notification_logged_in()
                                    }) {
                                    Text(
                                        color = Color.White,
                                        text = "Ok"
                                    )
                                }
                                Button(colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Blue
                                ),
                                    onClick = { dialogOpen = false }) {
                                    Text(
                                        color = Color.White,
                                        text = "Cancelar"
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
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    letterSpacing = 1.sp,
                                    text = "Escolha a Divisão: "
                                )

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    division = DropDownMenuOutlined(
                                        modifier1 = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                                        modifier2 = Modifier.padding(top = 50.dp),
                                        arrayOf("Cozinha", "Sala", "Quarto", "WC", "Garagem")
                                    )
                                }
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
                IconButton(
                    onClick = {
                        dialogOpen = true
                    }
                ) {
                    Icon(
                        Icons.Rounded.ArrowForward, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(start = 5.dp),
                    )
                }
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
                        Icons.Rounded.Add, "",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                },
                text = { Text("Novo", fontSize = 18.sp) },
                backgroundColor = Color.Gray,
                onClick = {
                    navController.navigate("NewDeviceScreen")
                },
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
fun UnconnectedDevicesScreenPreview() {
    UnconnectedDevicesScreen(
        mainActivity = MainActivity(),
        navController = NavController(LocalContext.current)
    )
}