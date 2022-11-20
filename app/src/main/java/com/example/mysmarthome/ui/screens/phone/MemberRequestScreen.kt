package com.example.mysmarthome.ui.screens.phone

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun MemberRequestScreen() {

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
                    modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                    fontSize = 22.sp,
                    text = "João " + stringResource(id = R.string.adesaoPedidos)
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
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 50.dp)
            ) {

                // Em vez de items(x) mete-se foreach ...
                LazyColumn {

                    items(15) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.Serif,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 7.dp, start = 20.dp)
                                    .width(screenWidth / 3),
                                fontSize = 18.sp,
                                text = "Maria"
                            )

                            dropDownMenuMembers()

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
                    onClick = { }
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

@SuppressLint("ResourceType")
@Composable
fun dropDownMenuMembers() {

    var expanded by remember { mutableStateOf(false) }
    val select: String = stringResource(id = R.string.select)
    val memberType: Array<String> = stringArrayResource(id = R.array.membersType)
    var selectedText by remember { mutableStateOf(select) }


    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(start = 15.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .width(300.dp)
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
            modifier = Modifier
                .width(220.dp)
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
    }
}