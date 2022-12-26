package com.example.mysmarthome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopbarBack(title: String, navController: NavController){
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

/*
@Composable
fun TopBarBackForward(
    iconForward: ImageVector,
    title: String,
    actionBack: () -> Unit,
    actionForward: () -> Unit
) {
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        IconButton(
            onClick = {
                actionBack()
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
            modifier = Modifier.padding(top = 7.dp, start = 10.dp),
            fontSize = 20.sp,
            text = title
        )
        IconButton(
            onClick = {
                actionForward()
            }
        ) {
            Icon(
                iconForward, "",
                tint = Color.Black,
                modifier = Modifier
                    .width(50.dp)
                    .padding(end = 10.dp),
            )
        }
    }
    DividerTopBar()
}

@Composable
fun Topbar2EndIcons(content: @Composable() () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        content()
    }
    DividerTopBar()
}

@Composable
fun DividerTopBar() {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Divider(
        startIndent = 20.dp,
        thickness = 1.dp,
        color = Color.Black,
        modifier = Modifier
            .padding(top = 70.dp)
            .width(screenWidth - 20.dp)
    )
}

 */