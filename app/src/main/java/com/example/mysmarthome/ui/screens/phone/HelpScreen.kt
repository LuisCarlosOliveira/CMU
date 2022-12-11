package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R

@Composable
fun HelpScreen(navController: NavController) {
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
                        onClick = {
                            navController.popBackStack()
                        }
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
                        modifier = Modifier.padding(top = 7.dp, end = 150.dp),
                        fontSize = 22.sp,
                        text = "Ajuda"
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
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Column(

                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp, bottom = 90.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = letterSpacing,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
                            fontSize = 14.sp,
                            text = "Veja como a nossa aplicação funciona:"
                        )
                    }

                    Column(

                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 30.dp, bottom = 90.dp)
                    ) {
                        addVideo()
                    }
                }
            }
        )
    }
}

@Composable
fun addVideo() {
    Image(
        painter = painterResource(id = R.drawable.video_icon),
        contentDescription = "Video",
        //contentScale = ContentScale.Crop
    )
}


@Preview()
@Composable
fun PreviewHelpScreen() {
    HelpScreen(navController = NavController(LocalContext.current))
}