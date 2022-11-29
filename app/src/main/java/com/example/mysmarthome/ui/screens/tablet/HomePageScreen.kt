package com.example.mysmarthome.ui.screens.tablet
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePageScreen(mainActivity: MainActivity, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.fillMaxWidth(),
                topBar = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        Text(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            letterSpacing = 1.sp,
                            text = "Bem-Vindo João!"
                        )
                    }
                },
                content = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        dropDownMenu()
                    }
                    Column(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 100.dp,
                            bottom = 70.dp
                        )
                    ) {
                        val list = arrayOfNulls<Number>(25)

                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 128.dp),
                        ) {
                            items(list.size) { l ->
                                Card(
                                    elevation = 10.dp,
                                    border = BorderStroke(1.dp, Color.Blue),
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .padding(end = 5.dp)
                                ) {
                                    Text(text = l.toString(), Modifier.padding(10.dp))
                                }
                            }
                            /*
                            items(1){
                                Card(
                                    elevation = 10.dp,
                                    border = BorderStroke(1.dp, Color.Blue),
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .padding(end = 5.dp)
                                ) {
                                    IconButton(
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Add, "",
                                            tint = Color.Black,
                                            modifier = Modifier.size(100.dp)
                                        )
                                        Text(
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            letterSpacing = 1.sp,
                                            text = "Nova",
                                            modifier = Modifier.padding(top=90.dp)
                                        )
                                    }
                                }
                            }
                            */
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
                        onClick = { },
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
