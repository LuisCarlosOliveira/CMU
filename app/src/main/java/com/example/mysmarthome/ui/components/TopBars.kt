package com.example.mysmarthome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
fun TopbarBack(
    title: String,
    navController: NavController
) {
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

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
            text = title
        )
    }
}

@Composable
fun TopBarBackForward(
    iconForward: ImageVector,
    title: String,
    navController: NavController,
    routeForward: String
) {
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
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
            modifier = Modifier.padding(top = 7.dp),
            fontSize = 24.sp,
            text = title
        )
        IconButton(
            onClick = {
                navController.navigate(routeForward)
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
fun DividerTopBar() {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Divider(
        startIndent = 20.dp,
        thickness = 1.dp,
        color = Color.Black,
        modifier = Modifier
            .padding(top = 60.dp)
            .width(screenWidth - 20.dp)
    )
}