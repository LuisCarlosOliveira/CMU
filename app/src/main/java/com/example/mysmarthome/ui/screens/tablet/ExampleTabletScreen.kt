package com.example.mysmarthome.ui.screens.tablet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mysmarthome.ui.components.Greeting

@Composable
fun ExampleTabletScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Greeting(name = "Hello World")
            Button(onClick = {
                //navController.navigate("QuestionsScreen")
            }) {
                Text("Hello")
            }
        }

    }
}