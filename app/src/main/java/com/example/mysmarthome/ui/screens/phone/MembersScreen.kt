package com.example.mysmarthome.ui.screens.phone

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R

@Composable
fun MembersScreen(mainActivity: MainActivity, navController: NavController) {
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

            var message by rememberSaveable {
                mutableStateOf("")
            }

            var contact by rememberSaveable {
                mutableStateOf("912852300")
            }

            var dialogOpen by remember { mutableStateOf(false) }
            var dialogOpenEdit by remember { mutableStateOf(false) }

            val ctx = LocalContext.current

            var letterSpacing by remember {
                mutableStateOf(1.sp)
            }

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
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
                                    .padding(start = 5.dp)
                            )
                        }
                        Text(
                            fontWeight = FontWeight.Medium,
                            letterSpacing = letterSpacing,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 7.dp),
                            fontSize = 22.sp,
                            text = "João " + stringResource(id = R.string.members)
                        )
                        IconButton(
                            onClick = {
                                navController.navigate("InviteMemberScreen")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.PersonAddAlt, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .width(50.dp)
                                    .padding(end = 5.dp)
                            )
                        }
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
                            .padding(bottom = 60.dp)
                            .fillMaxSize()
                    ) {
                        LazyColumn {
                            items(4) {
                                Row(
                                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp)
                                ) {
                                    Text(
                                        fontWeight = FontWeight.Medium,
                                        letterSpacing = letterSpacing,
                                        fontFamily = FontFamily.Serif,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(top = 7.dp, start = 10.dp)
                                            .width(screenWidth / 3),
                                        fontSize = 18.sp,
                                        text = "Maria"
                                    )

                                    IconButton(
                                        onClick = { dialogOpenEdit = true }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Edit,
                                            "",
                                            tint = Color.Black,

                                            )
                                    }

                                    if (dialogOpenEdit) {
                                        AlertDialog(
                                            onDismissRequest = { dialogOpenEdit = false },
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
                                                            text = "Guardar"
                                                        )
                                                    }
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = { dialogOpenEdit = false }) {
                                                        Text(
                                                            color = Color.White,
                                                            text = "Cancelar"
                                                        )
                                                    }
                                                }
                                            },

                                            title = { Text("Alterar Permissões de Membro") },

                                            text = {
                                                Column(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight()
                                                ) {
                                                    dropDownMenuMembers2(navController)
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
                                            mainActivity.callSomeone(contact)
                                        }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Phone, "",
                                            tint = Color.Black,

                                            )
                                    }

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
                                                            mainActivity.composeSMSMessage(
                                                                message,
                                                                contact
                                                            )
                                                        }) {
                                                        Text(
                                                            color = Color.White,
                                                            text = "Enviar"
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
                                                        text = "Inserir Mensagem: "
                                                    )

                                                    TextField(colors = TextFieldDefaults.textFieldColors(
                                                        focusedIndicatorColor = Color.Blue,
                                                        unfocusedIndicatorColor = Color.Blue,
                                                        disabledIndicatorColor = Color.Blue
                                                    ),
                                                        modifier = Modifier
                                                            .padding(
                                                                top = 10.dp,
                                                                bottom = 10.dp
                                                            )
                                                            .fillMaxWidth()
                                                            .height(120.dp),
                                                        value = message,
                                                        shape = RoundedCornerShape(7.dp),
                                                        maxLines = 3,
                                                        onValueChange = { message = it },
                                                        placeholder = { Text(text = "Insira a Mensagem") },
                                                        label = { Text(text = "Mensagem") })

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
                                        onClick = { dialogOpen = true }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Sms, "",
                                            tint = Color.Black,
                                        )
                                    }
                                    IconButton(
                                        onClick = { },

                                        ) {
                                        Icon(
                                            Icons.Rounded.DeleteForever, "",
                                            tint = Color.Black,
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp), horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate("HomePageScreen")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Home, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(top = 10.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                navController.navigate("ConnectedDevicesScreen")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Devices, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(top = 10.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                navController.navigate("ConsumptionsScreen")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Timeline, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(top = 10.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                navController.navigate("DefinitionsScreen")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Settings, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(top = 10.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}


@SuppressLint("ResourceType")
@Composable
fun dropDownMenuMembers2(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val memberType: Array<String> = stringArrayResource(id = R.array.membersType)
    val select: String = memberType[0]
    var selectedText by remember { mutableStateOf(select) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val localization = if (selectedText.equals("Residente Infantil"))
        Icons.Filled.LocationOn
    else
        null

    Column(Modifier.padding(start = 20.dp, top = 15.dp)) {
        Row() {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .width(200.dp)
                    .padding(end = 20.dp),
                label = { Text(stringResource(id = R.string.memberType)) },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(220.dp)
            ) {
                memberType.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
            IconButton(
                onClick = { }
            ) {
                if (localization != null) {
                    Icon(
                        localization, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 20.dp)
                            .clickable(onClick = { navController.navigate("LocationScreen") })
                    )
                }
            }
        }
    }
}