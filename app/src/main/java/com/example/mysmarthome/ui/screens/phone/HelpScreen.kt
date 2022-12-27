package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.AddImage
import com.example.mysmarthome.ui.components.TopBarBack

@Composable
fun HelpScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        var letterSpacing by remember {
            mutableStateOf(1.sp)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBack(
                    title = stringResource(id = R.string.helpTitle),
                    navController = navController
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
                            text = stringResource(id = R.string.descriptionHelp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 30.dp, bottom = 90.dp)
                    ) {
                        AddImage(
                            modifier = null,
                            painter = painterResource(id = R.drawable.video_icon)
                        )
                    }
                }
            }
        )
    }
}

@Preview()
@Composable
fun PreviewHelpScreen() {
    HelpScreen(navController = NavController(LocalContext.current))
}