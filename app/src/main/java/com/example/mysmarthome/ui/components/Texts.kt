package com.example.mysmarthome.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PersonalText(fontWeight: FontWeight, modifier: Modifier?, text: String) {

    var letterSpacing by remember { mutableStateOf(1.sp) }

    if (modifier != null) {
        Text(
            fontWeight = fontWeight,
            letterSpacing = letterSpacing,
            fontFamily = FontFamily.SansSerif,
            modifier = modifier,
            fontSize = 17.sp,
            text = text
        )

    } else {
        Text(
            fontWeight = fontWeight,
            letterSpacing = letterSpacing,
            fontFamily = FontFamily.SansSerif,
            fontSize = 17.sp,
            text = text
        )
    }

}