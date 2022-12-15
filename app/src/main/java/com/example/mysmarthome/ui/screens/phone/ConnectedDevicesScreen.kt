package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import androidx.navigation.NavController
import com.example.mysmarthome.R

@Composable
fun ConnectedDevicesScreen(navController: NavController) {

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
                    text = stringResource(id = R.string.connectedDevicesTitle)
                )
            }

            Divider(
                startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                    .padding(top = 70.dp)
                    .width(screenWidth - 20.dp)
            )

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
                text = { Text("Adicionar", fontSize = 18.sp) },
                backgroundColor = Color.Gray,
                onClick = {
                    navController.navigate("UnconnectedDevicesScreen")
                },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
        },
        content = {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    text = "Filtrar por divisão:",
                    maxLines = 2,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp)

                )
                dropFilterByDivision()
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 15.dp)
            ) {

                Spacer(modifier = Modifier.height(50.dp))
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
                                text = "Dispositivo "
                            )

                            Icon(
                                Icons.Rounded.Delete, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .width(50.dp)
                                    .padding(top = 15.dp, end = 20.dp)
                                    .clickable { },
                            )

                        }
                    }
                }
            }
        },
    )
}

@Composable
fun dropFilterByDivision() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Todos","Cozinha", "WC", "Sala de Jantar", "Garagem")
    var selectedText by remember { mutableStateOf("Todos") }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(5.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
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


@Preview(showBackground = true)
@Composable
fun ConnectedDevicesScreenPreview() {
    ConnectedDevicesScreen(navController = NavController(LocalContext.current))
}