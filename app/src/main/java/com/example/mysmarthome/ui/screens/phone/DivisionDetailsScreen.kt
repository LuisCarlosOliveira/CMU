package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DivisionDetailsScreen(/*mainActivity: MainActivity ,navController: NavController*/) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

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
                    horizontalArrangement = Arrangement.SpaceBetween,
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
                        modifier = Modifier.padding(top = 7.dp, end = 150.dp),
                        fontSize = 22.sp,
                        text = "Garagem"
                    )
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Rounded.Delete, "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(50.dp)
                                .size(60.dp)
                                .padding(end = 30.dp),
                        )
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Rounded.Edit, "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(50.dp)
                                .size(60.dp)
                                .padding(end = 20.dp),
                        )
                    }

                    var dialogOpen by remember { mutableStateOf(false) }



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
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp, bottom = 50.dp)
                    ) {
                        addImage()
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 20.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .height(35.dp)
                        ) {
                            Text(text = "Editar Imagem")
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 40.dp, bottom = 60.dp)
                    ) {
                        dropFilterByDevice()
                        dropFilterByDevice()
                        dropFilterByDevice()
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            Icons.Rounded.Star, "",
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )
                    },
                    text = { Text("Aplicar Minhas Preferências", fontSize = 18.sp) },
                    backgroundColor = Color.Gray,
                    onClick = { },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                )

            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Composable
fun dropFilterByDevice() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Iluminação", "Estoros", "Tomadas")
    var selectedText by remember { mutableStateOf("Estoros") }

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


@Preview()
@Composable
fun PreviewDivisionDetailsScreen() {
    DivisionDetailsScreen()
}