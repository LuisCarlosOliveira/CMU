package com.example.mysmarthome.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListRowWithCheckbox(title: String) {

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

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
            //color = Color.Black,
            modifier = Modifier
                .padding(top = 15.dp, start = 20.dp),
            fontSize = 18.sp,
            text = title
        )
        CheckBoxButton()
    }
}

@Composable
fun ListRowWithIcon(title: String, icon: ImageVector, action: ()-> Unit) {

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

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
            //color = Color.Black,
            modifier = Modifier
                .padding(top = 15.dp, start = 20.dp),
            fontSize = 18.sp,
            text = title
        )
        IconButton(
            onClick = {
                action()
            }
        ) {
            Icon(
                icon, "",
                //tint = Color.Black,
                modifier = Modifier
                    .width(50.dp)
                    .padding(top = 15.dp, end = 20.dp),
            )
        }
    }
}