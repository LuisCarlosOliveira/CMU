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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun MembersScreen() {
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
                            onClick = { }
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
                            onClick = { }
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
                    LazyColumn {
                        items(4) {
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 20.dp)
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
                                Row(horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()) {
                                    dropDownMenuMembers2()

                                    IconButton(
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Phone, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(55.dp)
                                                .padding(top = 35.dp, end = 15.dp)
                                        )
                                    }

                                    IconButton(
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Sms, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(55.dp)
                                                .padding(top = 35.dp, end = 15.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.DeleteForever, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(65.dp)
                                                .padding(top = 35.dp, end = 15.dp)
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
    }
}


@SuppressLint("ResourceType")
@Composable
fun dropDownMenuMembers2() {
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
                    )
                }
            }
        }
    }
}