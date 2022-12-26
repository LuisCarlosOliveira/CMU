package com.example.mysmarthome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopBarBack(title: String, navController: NavController){
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }
    TopAppBar(
        title = {
            Text(
                fontWeight = FontWeight.Medium,
                letterSpacing = letterSpacing,
                fontFamily = FontFamily.Serif,
                color = Color.Black,
                fontSize = 22.sp,
                text = title
            )
        },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Rounded.ArrowBack, "", tint = Color.Black)
            }
        },
        modifier = Modifier.height(70.dp)
    )
}

@Composable
fun TopBarBackForward(title :String, actionBtns: @Composable()()->Unit, navController: NavController){
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }
    TopAppBar(
        title = {
            Text(
                fontWeight = FontWeight.Medium,
                letterSpacing = letterSpacing,
                fontFamily = FontFamily.Serif,
                color = Color.Black,
                fontSize = 22.sp,
                text = title
            )
        },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Rounded.ArrowBack, "", tint = Color.Black)
            }
        },
        actions = {
            actionBtns()
        },
        modifier = Modifier.height(70.dp)
    )
}