package com.example.mysmarthome.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun ChooseButton(title: String, action: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        border = BorderStroke(1.dp, Color.Blue),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(250.dp)
            .height(80.dp),
        onClick = {
            action()
        }) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun NormalButton(modifier: Modifier?, action: () -> Unit, title: String) {
    if (modifier != null) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                action()
            },
            modifier = modifier
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color.White,
                text = title
            )
        }
    } else {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                action()
            }
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color.White,
                text = title
            )
        }
    }
}

@Composable
fun FloatingButton(icon: ImageVector?, title: String?, action: () -> Unit) {
    ExtendedFloatingActionButton(
        icon = {
            if (icon != null) {
                Icon(
                    icon, "",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        },
        text = {
            if (title != null) {
                Text(title, fontSize = 18.sp)
            }
        },
        backgroundColor = Color.LightGray,
        onClick = {
            action()
        },
        modifier = Modifier.padding(bottom = 10.dp),
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    )
}

@Composable
fun LoginSigninButton(title: String, action: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            action()
        },

        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 16.sp,
            color = Color.White,
            text = title
        )
    }
}

@Composable
fun CheckBoxButton() {
    val checkedState = remember { mutableStateOf(false) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Composable
fun SwitchBtn(ison: Boolean): Boolean {
    val on_off = remember { mutableStateOf(ison) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Switch(
        checked = on_off.value,
        onCheckedChange = { on_off.value = it },
        modifier = Modifier
            .padding(end = 160.dp)
            .width(screenWidth / 2),
    )
    return on_off.value
}