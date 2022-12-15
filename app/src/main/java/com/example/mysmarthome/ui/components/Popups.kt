package com.example.mysmarthome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AlertPopup(
    state: Boolean,
    btn1Text: String,
    btn2Text: String,
    content: @Composable() () -> Unit,
    actionBtn: () -> Unit,
    actionBtn2: () -> Unit
) {
    var dialogOpen by remember { mutableStateOf(false) }
    dialogOpen = state
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = { dialogOpen = false },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    ),
                        onClick = {
                            actionBtn()
                        }) {
                        Text(
                            color = Color.White,
                            text = btn1Text
                        )
                    }
                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    ),
                        onClick = { actionBtn2() }) {
                        Text(
                            color = Color.White,
                            text = btn2Text
                        )
                    }
                }
            },

            title = { },

            text = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    content()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}

/*
@Composable
fun InsertPopup(state:Boolean,btn1Text:String, btn2Text:String, actionBtn:()->Unit, actionBtn2:()->Unit,content: @Composable()()->Unit){
    var dialogOpen by remember { mutableStateOf(false) }
    dialogOpen = state
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = { dialogOpen = false },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    ),
                        onClick = {
                            actionBtn()
                        }) {
                        Text(
                            color = Color.White,
                            text = btn1Text
                        )
                    }
                    Button(colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    ),
                        onClick = { actionBtn2() }) {
                        Text(
                            color = Color.White,
                            text = btn2Text
                        )
                    }
                }
            },

            title = { },

            text = {
                content()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}

@Composable
fun T111(){
    FormNumberTextField(title = "" , placeholder = "", label ="" )
}

*/