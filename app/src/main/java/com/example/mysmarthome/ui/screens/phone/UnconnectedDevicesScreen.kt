package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.mysmarthome.ui.components.FloatingButton
import com.example.mysmarthome.ui.components.ListRowWithCheckbox
import com.example.mysmarthome.ui.components.TopBarBackForward

@Composable
fun UnconnectedDevicesScreen(mainActivity: MainActivity, navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    var division by remember {
        mutableStateOf("")
    }

    var dialogOpen by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBackForward(
                iconForward = Icons.Rounded.ArrowForward,
                title = stringResource(id = R.string.unconnectedDevicesTitle),
                actionBack = { navController.popBackStack() },
                actionForward = { dialogOpen = true })
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
                                    navController.navigate("HomePageScreen/"+1+"/"+2)
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
        },
        
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {

                LazyColumn {
                    items(15) {
                       ListRowWithCheckbox(title = stringResource(id = R.string.deviceTitleRow))
                    }
                }
            }
        },

        floatingActionButton = {
            FloatingButton(
                icon = Icons.Rounded.Add,
                title = stringResource(id = R.string.titleBtnUnconnDev),
                action = { navController.navigate("NewDeviceScreen") })
        },
        floatingActionButtonPosition = FabPosition.Center,
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