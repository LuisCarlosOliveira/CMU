package com.example.mysmarthome.ui.screens.phone

import android.annotation.SuppressLint
import android.widget.SeekBar
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun DefinitionsDivisionsDevicesScreens() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val onOff = remember { mutableStateOf(true) }
    var sliderPosition by remember { mutableStateOf(0f) }
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
                    text = "Luz Direita Teto"
                )

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        Icons.Rounded.Star, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 15.dp),
                    )
                }
            }

            Divider(
                startIndent = 20.dp, thickness = 1.dp, color = Color.Black, modifier = Modifier
                    .padding(top = 70.dp)
                    .width(screenWidth - 20.dp)
            )
            Spacer(Modifier.padding(60.dp))
        },
        content = {
            Column(
                modifier = Modifier.padding(start = 20.dp)) {
                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = letterSpacing,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 7.dp),
                        fontSize = 17.sp,
                        text = "On/Off:  "
                    )
                    Switch(
                        checked = onOff.value,
                        onCheckedChange = { onOff.value = it },
                        modifier = Modifier.padding(top = 7.dp),
                        )
                }

                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = letterSpacing,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 6.dp),
                        fontSize = 17.sp,
                        text = "Cor:         "
                    )
                    dropDownMenuColors()
                }
                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = letterSpacing,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 7.dp),
                        fontSize = 17.sp,
                        text = "Brilho:        "
                    )

                    Slider(
                        modifier = Modifier.padding(end = 15.dp),
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        valueRange = 0f..10f,
                        onValueChangeFinished = {
                            // launch some business logic update with the state you hold
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        steps = 9
                    )
                }
                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = letterSpacing,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 7.dp),
                        fontSize = 17.sp,
                        text = "Potencia:    "
                    )

                    Slider(
                        modifier = Modifier.padding(end = 15.dp),
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        valueRange = 0f..10f,
                        onValueChangeFinished = {
                            // launch some business logic update with the state you hold
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        steps = 9
                    )
                }
                Spacer(Modifier.padding(20.dp))
                Box(modifier = Modifier.padding(80.dp)) {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.width(200.dp).height(40.dp),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = "Guardar Alterações",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

    )
}

@SuppressLint("ResourceType")
@Composable
fun dropDownMenuColors() {
    var expanded by remember { mutableStateOf(false) }
    val select: String = stringResource(id = R.string.select)
    var selectedText by remember { mutableStateOf(select) }
    val colorType: Array<String> = arrayOf("Azul", "Branco", "Verde", "Vermelho")

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
            colorType.forEach { label ->
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

