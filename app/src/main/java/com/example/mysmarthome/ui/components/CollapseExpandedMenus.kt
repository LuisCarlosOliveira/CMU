package com.example.mysmarthome.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CollapsableLazyColumn(
    navController: NavController,
    sections: List<CollapsableSection>,
    modifier: Modifier = Modifier,
    devicesIds: MutableMap<String, String>
) {

    val collapsedState = remember(sections) {
        sections.map { true }.toMutableStateList()
    }

    LazyColumn(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            collapsedState[i] = !collapsed
                        }
                        .background(Color.LightGray)
                ) {
                    Icon(
                        Icons.Default.run {
                            if (collapsed)
                                KeyboardArrowDown
                            else
                                KeyboardArrowUp
                        },
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier.padding(start = 7.dp, end = 10.dp)
                    )
                    Text(
                        dataItem.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .weight(1f),
                    )
                }
                Divider()
                Spacer(Modifier.padding(top = 7.dp))
            }
            if (!collapsed) {
                items(dataItem.rows) { row ->
                    if(row.isNotEmpty()) {
                        Row(modifier = Modifier.clickable(
                            onClick = {
                                var deviceId = devicesIds[row]
                                Log.d("AQUIII", deviceId.toString())
                                if (deviceId != null) {
                                    if (dataItem.title.equals("Luzes")) {
<<<<<<< Updated upstream
                                        navController.navigate("LightScreen")
                                    } else if (dataItem.title.equals("Estoros")) {
                                        navController.navigate("BlindScreen")
                                    } else {
                                        navController.navigate("PlugScreen")
=======
                                        navController.navigate("LightScreen/"+deviceId)
                                    } else if (dataItem.title.equals("Estoros")) {
                                        navController.navigate("BlindScreen/"+deviceId)
                                    } else {
                                        navController.navigate("PlugScreen/"+deviceId)
>>>>>>> Stashed changes
                                    }
                                }
                            }
                        )) {
                            Spacer(modifier = Modifier.size(MaterialIconDimension.dp))
                            Text(
                                row,
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )
                        }
                    }
                    Divider()
                }
            }
        }
    }
}

data class CollapsableSection(val title: String, val rows: Array<String>)

const val MaterialIconDimension = 24f