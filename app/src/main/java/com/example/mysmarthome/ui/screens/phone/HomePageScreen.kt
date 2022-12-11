package com.example.mysmarthome.ui.screens.phone

import WindowInfo
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.screens.tablet.dropDownMenu
import kotlinx.coroutines.launch
import rememberWindowInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePageScreen(mainActivity: MainActivity, navController: NavController) {
    //consoante o windowInfo, é mostrado uma composable diferente
    val windownInfo = rememberWindowInfo()

    if( windownInfo.screenWidthInfo is WindowInfo.WindowType.Compact ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                val scope = rememberCoroutineScope()

                var dialogOpenAlarm by remember { mutableStateOf(false) }
                var dialogOpenAgenda by remember { mutableStateOf(false) }

                var message by rememberSaveable {
                    mutableStateOf("")
                }
                var hour by rememberSaveable {
                    mutableStateOf("")
                }
                var minute by rememberSaveable {
                    mutableStateOf("")
                }
                var title by rememberSaveable {
                    mutableStateOf("")
                }
                var locationEvent by rememberSaveable {
                    mutableStateOf("")
                }
                var description by rememberSaveable {
                    mutableStateOf("")
                }

                Box() {
                    Spacer(modifier = Modifier.padding(start = 5.dp))

                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Menu, "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(50.dp)
                                .padding(start = 5.dp, end = 5.dp, top = 30.dp),
                        )
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxWidth(),
                    topBar = {
                        Row(
                            horizontalArrangement = Arrangement.Center, modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        ) {
                            items(list.size) { l ->
                                Card(
                                    elevation = 10.dp,
                                    border = BorderStroke(1.dp, Color.Blue),
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .padding(end = 5.dp)
                                ) {
                                    IconButton(
                                        onClick = {
                                            navController.navigate("DivisionDetailsScreen")
                                        }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Home, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .width(50.dp)
                                                .padding(start = 5.dp),
                                        )
                                    }
                                }
                            }
                        }
                    },

                    drawerContent = {
                        Column(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .fillMaxHeight()
                        ) {

                        Text(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 20.dp, top = 30.dp, start = 10.dp),
                            letterSpacing = 1.sp,
                            text = stringResource(id = R.string.utilities)
                        )
                        Divider()
                        Column(modifier = Modifier.padding(top = 15.dp)) {
                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = { dialogOpenAlarm = true })) {

                                Spacer(modifier = Modifier.padding(start = 20.dp))

                                if (dialogOpenAlarm) {
                                    AlertDialog(
                                        onDismissRequest = { dialogOpenAlarm = false },
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
                                                        dialogOpenAlarm = false
                                                        mainActivity.createAlarm(
                                                            message,
                                                            hour.toInt(),
                                                            minute.toInt()
                                                        )
                                                    }) {
                                                    Text(color = Color.White, text = "Criar")
                                                }
                                                Button(colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color.Blue
                                                ),
                                                    onClick = { dialogOpenAlarm = false }) {
                                                    Text(color = Color.White, text = "Cancelar")
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

                                                TextField(colors = TextFieldDefaults.textFieldColors(
                                                    focusedIndicatorColor = Color.Blue,
                                                    unfocusedIndicatorColor = Color.Blue,
                                                    disabledIndicatorColor = Color.Blue
                                                ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(bottom = 5.dp),
                                                    horizontalArrangement = Arrangement.SpaceEvenly
                                                ) {
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = {
                                                            dialogOpenAlarm = false
                                                            mainActivity.createAlarm(
                                                                message,
                                                                hour.toInt(),
                                                                minute.toInt()
                                                            )
                                                        }) {
                                                        Text(color = Color.White, text = "Criar")
                                                    }
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = { dialogOpenAlarm = false }) {
                                                        Text(color = Color.White, text = "Cancelar")
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

                                                    TextField(colors = TextFieldDefaults.textFieldColors(
                                                        focusedIndicatorColor = Color.Blue,
                                                        unfocusedIndicatorColor = Color.Blue,
                                                        disabledIndicatorColor = Color.Blue
                                                    ),
                                                    modifier = Modifier
                                                        .padding(top = 10.dp, bottom = 10.dp)
                                                        .fillMaxWidth(),
                                                    value = hour,
                                                    shape = RoundedCornerShape(7.dp),
                                                    onValueChange = { hour = it },
                                                    placeholder = { Text(text = "Insira a Hora") },
                                                    label = { Text(text = "Hora") },
                                                    keyboardOptions = KeyboardOptions(
                                                        keyboardType = KeyboardType.Number
                                                    )
                                                )

                                                TextField(
                                                    colors = TextFieldDefaults.textFieldColors(
                                                        focusedIndicatorColor = Color.Blue,
                                                        unfocusedIndicatorColor = Color.Blue,
                                                        disabledIndicatorColor = Color.Blue
                                                    ),
                                                    modifier = Modifier
                                                        .padding(bottom = 10.dp)
                                                        .fillMaxWidth(),
                                                    value = minute,
                                                    shape = RoundedCornerShape(7.dp),
                                                    onValueChange = { minute = it },
                                                    placeholder = { Text(text = "Insira os Minutos") },
                                                    label = { Text(text = "Minutos") },
                                                    keyboardOptions = KeyboardOptions(
                                                        keyboardType = KeyboardType.Number
                                                    )
                                                )

                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(400.dp)
                                                .padding(2.dp),
                                            shape = RoundedCornerShape(10.dp),
                                            backgroundColor = Color.White,
                                            properties = DialogProperties(
                                                dismissOnBackPress = true,
                                                dismissOnClickOutside = true
                                            )
                                        )
                                    )
                                }
                                IconButton(
                                    onClick = { }
                                ) {
                                    Icon(
                                        Icons.Rounded.Timer, "",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .width(50.dp)
                                            .padding(bottom = 25.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                Text(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    letterSpacing = 1.sp,
                                    text = stringResource(id = R.string.createAlarm)
                                )
                            }
                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .clickable(onClick = { dialogOpenAgenda = true })
                            ) {

                                Spacer(modifier = Modifier.padding(start = 20.dp))

                                if (dialogOpenAgenda) {
                                    AlertDialog(
                                        onDismissRequest = { dialogOpenAgenda = false },
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
                                                        dialogOpenAgenda = false
                                                        mainActivity.addEvent(
                                                            title,
                                                            locationEvent,
                                                            description
                                                        )
                                                    }) {
                                                    Text(color = Color.White, text = "Agendar")
                                                }
                                                Button(colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color.Blue
                                                ),
                                                    onClick = { dialogOpenAgenda = false }) {
                                                    Text(color = Color.White, text = "Cancelar")
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

                                                TextField(colors = TextFieldDefaults.textFieldColors(
                                                    focusedIndicatorColor = Color.Blue,
                                                    unfocusedIndicatorColor = Color.Blue,
                                                    disabledIndicatorColor = Color.Blue
                                                ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(bottom = 5.dp),
                                                    horizontalArrangement = Arrangement.SpaceEvenly
                                                ) {
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = {
                                                            dialogOpenAlarm = false
                                                            mainActivity.addEvent(
                                                                title,
                                                                locationEvent,
                                                                description
                                                            )
                                                        }) {
                                                        Text(color = Color.White, text = "Agendar")
                                                    }
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = { dialogOpenAgenda = false }) {
                                                        Text(color = Color.White, text = "Cancelar")
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

                                                    TextField(colors = TextFieldDefaults.textFieldColors(
                                                        focusedIndicatorColor = Color.Blue,
                                                        unfocusedIndicatorColor = Color.Blue,
                                                        disabledIndicatorColor = Color.Blue
                                                    ),
                                                        modifier = Modifier
                                                            .padding(top = 10.dp)
                                                            .fillMaxWidth(),
                                                        value = title,
                                                        shape = RoundedCornerShape(7.dp),
                                                        onValueChange = { title = it },
                                                        placeholder = { Text(text = "Insira o Título") },
                                                        label = { Text(text = "Título") })

                                                    TextField(
                                                        colors = TextFieldDefaults.textFieldColors(
                                                            focusedIndicatorColor = Color.Blue,
                                                            unfocusedIndicatorColor = Color.Blue,
                                                            disabledIndicatorColor = Color.Blue
                                                        ),
                                                        modifier = Modifier
                                                            .padding(top = 10.dp, bottom = 10.dp)
                                                            .fillMaxWidth(),
                                                        value = locationEvent,
                                                        shape = RoundedCornerShape(7.dp),
                                                        onValueChange = { locationEvent = it },
                                                        placeholder = { Text(text = "Insira o Local") },
                                                        label = { Text(text = "Local") },

                                                        )

                                                    TextField(
                                                        colors = TextFieldDefaults.textFieldColors(
                                                            focusedIndicatorColor = Color.Blue,
                                                            unfocusedIndicatorColor = Color.Blue,
                                                            disabledIndicatorColor = Color.Blue
                                                        ),
                                                        modifier = Modifier
                                                            .padding(bottom = 10.dp)
                                                            .fillMaxWidth()
                                                            .height(100.dp),
                                                        value = description,
                                                        shape = RoundedCornerShape(7.dp),
                                                        maxLines = 3,
                                                        onValueChange = { description = it },
                                                        placeholder = { Text(text = "Insira a Descrição") },
                                                        label = { Text(text = "Descrição") },

                                                        )
                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(400.dp)
                                                .padding(2.dp),
                                            shape = RoundedCornerShape(10.dp),
                                            backgroundColor = Color.White,
                                            properties = DialogProperties(
                                                dismissOnBackPress = true,
                                                dismissOnClickOutside = true
                                            )
                                        )
                                    )
                                }
                                IconButton(
                                    onClick = { }
                                ) {
                                    Icon(
                                        Icons.Rounded.CalendarToday, "",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .width(50.dp)
                                            .padding(bottom = 25.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                Text(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    letterSpacing = 1.sp,
                                    text = stringResource(id = R.string.addEvent)
                                )
                            }
                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            Row(Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = { mainActivity.homeLocation("R. Carvalhais 56, 4780-564 Santo Tirso, Portugal") }
                                )) {
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                IconButton(
                                    onClick = { }
                                ) {
                                    Icon(
                                        Icons.Rounded.PinDrop, "",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .width(50.dp)
                                            .padding(bottom = 25.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.padding(start = 20.dp))
                                Text(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    letterSpacing = 1.sp,
                                    text = stringResource(id = R.string.homeLocation)
                                )
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
                        text = { Text("Nova", fontSize = 18.sp) },
                        backgroundColor = Color.Gray,
                        onClick = {
                            navController.navigate("NewDivisionScreen")
                        },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp)
                    )
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
                                navController.navigate("ProfileScreen")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.AccountCircle, "",
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

                            IconButton(
                                onClick = { }
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
                                onClick = { }
                            ) {
                                Icon(
                                    Icons.Rounded.AccountCircle, "",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(top = 10.dp)
                                )
                            }
                            IconButton(
                                onClick = { }
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
                                onClick = { }
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
    }else if( windownInfo.screenWidthInfo is WindowInfo.WindowType.Medium ){
        //introduzir view para Medium
    }else{
        //introduzir view para Expanded
    }
}

@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("CASA SILVA", "CASA SILVA 2")
    var selectedText by remember { mutableStateOf("CASA SILVA") }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                text = selectedText,
                maxLines = 2,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(end = 20.dp)
            )
            Icon(icon, "contentDescription",
                Modifier
                    .clickable { expanded = !expanded })
            Box(modifier = Modifier.padding(top = 50.dp)) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
        }
    }
}