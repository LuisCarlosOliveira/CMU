package com.example.mysmarthome.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun AddImage(modifier: Modifier?, painter: Painter){
    if (modifier != null) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = modifier
        )
    }
}