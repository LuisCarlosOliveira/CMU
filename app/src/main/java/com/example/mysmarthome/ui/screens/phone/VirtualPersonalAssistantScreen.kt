package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun VirtualPersonalAssistantScreen(/*mainActivity: MainActivity ,navController: NavController*/) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp


        var letterSpacing by remember {
            mutableStateOf(1.sp)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
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
                        modifier = Modifier.padding(top = 7.dp, end = 20.dp),
                        fontSize = 22.sp,
                        text = "Assistente Virtual Pessoal"
                    )



                }

                Divider(
                    startIndent = 20.dp,
                    thickness = 1.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 70.dp)
                        .width(screenWidth - 20.dp)
                )

            },
            content = {



                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 200.dp, bottom = 90.dp)
                ) {
                    addAssistant()
                }



            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            Icons.Rounded.Mic, "",
                            modifier = Modifier.size(60.dp),
                            tint = Color.Black
                        )
                    },
                    onClick = { },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                    text = {}
                )

            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Composable
fun addAssistant(){
    Image(
        painter = painterResource(id = R.drawable.personal_assistant),
        contentDescription = "Personal Assistant",
        //contentScale = ContentScale.Crop

    )
}


@Preview()
@Composable
fun PreviewVirtualPersonalAssistantScreen() {
    VirtualPersonalAssistantScreen()
}