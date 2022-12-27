package com.example.mysmarthome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.skydoves.colorpicker.compose.*
import kotlin.math.roundToInt

@Composable
fun ColorPicker(navController: NavController): MutableList<Int> {

    val controller = rememberColorPickerController()
    var r1 by remember {
        mutableStateOf<Int>(0)
    }
    var g1 by remember {
        mutableStateOf<Int>(0)
    }
    var b1 by remember {
        mutableStateOf<Int>(0)
    }
    var a1 by remember {
        mutableStateOf<Int>(0)
    }
    var cor = mutableListOf<Int>(0, 0, 0, 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 5.dp)//30
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)//60
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)//200
                .padding(10.dp),
            controller = controller,
            onColorChanged = {
                var (r, g, b, a) = it.color
                r1 = (r * 255).roundToInt()
                g1 = (g * 255).roundToInt()
                b1 = (b * 255).roundToInt()
                a1 = (a * 255).roundToInt()
                cor.removeAll { true }
                cor.add(r1)
                cor.add(g1)
                cor.add(b1)
                cor.add(a1)
            }
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)//10
                .height(25.dp),//35
            controller = controller,
            tileOddColor = Color.White,
            tileEvenColor = Color.Black,

            )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)//10
                .height(25.dp),//35
            controller = controller,
        )
    }
    return cor
}