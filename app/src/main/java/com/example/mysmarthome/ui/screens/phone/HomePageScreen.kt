package com.example.mysmarthome.ui.screens.phone

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.DivisionsViewModel
import com.example.mysmarthome.database.view_models.HomesViewModel
import com.example.mysmarthome.database.view_models.UsersViewModel
import com.example.mysmarthome.ui.components.BottombarWithoutHome
import com.example.mysmarthome.ui.components.FloatingButton

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomePageScreen(mainActivity: MainActivity, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            val usersViewModel: UsersViewModel = viewModel(LocalContext.current as MainActivity)
            val user = usersViewModel.user.observeAsState()

            val homesViewModel: HomesViewModel = viewModel(LocalContext.current as MainActivity)
            val home = homesViewModel.home.observeAsState()
            LaunchedEffect(Unit ){ homesViewModel.getFirstHome()}

            val divisionsViewModel: DivisionsViewModel = viewModel(LocalContext.current as MainActivity)
            divisionsViewModel.getDivisions()
            val divisions = divisionsViewModel.allDivisions.observeAsState()

            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box() {
                    Spacer(modifier = Modifier.padding(start = 5.dp))
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(start = 5.dp, end = 5.dp, top = 30.dp),
                    ) {
                        Icon(
                            Icons.Filled.Menu, "",
                            tint = Color.Black,

                            )
                    }
                }
                Box() {
                    Spacer(modifier = Modifier.padding(start = 5.dp))
                    IconButton(
                        onClick = {
                            usersViewModel.logout()
                            navController.navigate("LoginScreen")
                        },
                        modifier = Modifier
                            .width(50.dp)
                            .padding(start = 5.dp, end = 5.dp, top = 30.dp),
                    ) {
                        Icon(
                            Icons.Filled.Logout, "",
                            tint = Color.Black,
                        )
                    }
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

                        Text(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            letterSpacing = 1.sp,
                            text = stringResource(id = R.string.welcome) + " " + user.value?.name + "!"
                        )
                    }
                },
                content = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(Modifier.padding(10.dp))
                        Text(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            letterSpacing = 1.sp,
                            text = "Casa " + home.value?.name
                        )

                    }
                    if(divisions.value != null){
                        val colorArray: Array<Color> = arrayOf(Color.Blue, Color.Red, Color.Black, Color.Green)
                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(minSize = 128.dp),
                                modifier = Modifier.padding(top=100.dp)
                            ) {
                                items(divisions.value!!.size) { l ->
                                    Card(
                                        elevation = 10.dp,
                                        border = BorderStroke(1.dp, Color.Blue),
                                        backgroundColor = colorArray.random(),
                                        modifier = Modifier
                                            .aspectRatio(1f)
                                            .padding(5.dp)
                                            .clickable(onClick = {
                                                divisionsViewModel.getOneDivision(divisions.value!!.get(l).idDivision)
                                                navController.navigate("DivisionDetailsScreen/"+ divisions.value!!.get(l).idDivision)
                                            })
                                    ) {
                                        Text(
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(top = 50.dp),
                                            fontSize = 20.sp,
                                            letterSpacing = 1.sp,
                                            text = divisions.value!!.get(l).name
                                        )
                                    }
                                }
                            }

                    }else{
                        Text(text = "Ainda não tem divisoes")
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(onClick = { dialogOpenAlarm = true })
                            ) {

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
                                                        .padding(top = 10.dp)
                                                        .fillMaxWidth(),
                                                    value = message,
                                                    shape = RoundedCornerShape(7.dp),
                                                    onValueChange = { message = it },
                                                    placeholder = { Text(text = "Insira a Mensagem") },
                                                    label = { Text(text = "Mensagem") })

                                                TextField(
                                                    colors = TextFieldDefaults.textFieldColors(
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
                            val address = home.value?.address
                            Row(Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        if (address != null) {
                                            mainActivity.homeLocation(address.street + ", " + address.postalCode + " " + address.city + ", " + address.country)
                                        }
                                    }
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
                    FloatingButton(
                        icon = Icons.Rounded.Add,
                        title = stringResource(id = R.string.newDivisionHomeBtn),
                        action = { navController.navigate("NewDivisionScreen") })
                },
                bottomBar = {
                    BottombarWithoutHome(navController = navController)
                }
            )
        }
    }
}