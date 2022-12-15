package com.example.mysmarthome.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottombarWithoutHome(navController: NavController) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        IconButton(
            onClick = {
                navController.navigate("ConnectedDevicesScreen")
            }
        ) {
            Icon(
                Icons.Rounded.Devices, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("ProfileScreen")
            }
        ) {
            Icon(
                Icons.Rounded.AccountCircle, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("ConsumptionsScreen")
            }
        ) {
            Icon(
                Icons.Rounded.Timeline, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("DefinitionsScreen")
            }
        ) {
            Icon(

                Icons.Rounded.Settings, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun BottombarWithHome(navController: NavController) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(60.dp), horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = {
                navController.navigate("HomePageScreen")
            }
        ) {
            Icon(
                Icons.Rounded.Home, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("ConnectedDevicesScreen")
            }
        ) {
            Icon(
                Icons.Rounded.Devices, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("ConsumptionsScreen")
            }
        ) {
            Icon(
                Icons.Rounded.Timeline, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("DefinitionsScreen")
            }
        ) {
            Icon(
                Icons.Rounded.Settings, "",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
        }
    }
}