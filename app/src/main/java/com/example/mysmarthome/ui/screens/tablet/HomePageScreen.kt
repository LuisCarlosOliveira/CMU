package com.example.mysmarthome.ui.screens.tablet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePageScreen() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            ) {
                Text(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    letterSpacing = 1.sp,
                    text = stringResource(id = R.string.welcome) + "João!"
                )
            }
        },
        content = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                dropDownMenuHomePage()
            }
            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    top = 180.dp,
                    bottom = 70.dp
                )
            ) {
                val list = arrayOfNulls<Number>(5)

                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 280.dp),
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
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.size(80.dp),
                icon = {
                    Icon(
                        Icons.Rounded.Add, "",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(start = 10.dp),
                        tint = Color.Black
                    )
                },
                text = { },
                backgroundColor = Color.Gray,
                onClick = { },
                elevation = FloatingActionButtonDefaults.elevation(10.dp)
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


@Composable
fun dropDownMenuHomePage() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("CASA SILVA", "CASA SILVA 2")
    var selectedText by remember { mutableStateOf("CASA SILVA") }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
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