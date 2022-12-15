package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.mysmarthome.ui.components.BottombarWithHome
import com.example.mysmarthome.ui.components.TopBarBackForward

@Composable
fun AboutScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBackForward(
                Icons.Filled.Help,
                stringResource(id = R.string.aboutTitle),
                { navController.popBackStack() },
                { navController.navigate("HelpScreen") })
        },

        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AddImage(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = R.drawable.logo)
                    )
                }
                Text(
                    letterSpacing = letterSpacing,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(top = 20.dp, start = 15.dp, bottom = 15.dp),
                    fontSize = 15.sp,
                    text = stringResource(id = R.string.contentAbout)
                )
            }
        },
        bottomBar = {
            BottombarWithHome(navController = navController)
        }
    )
}

@Preview()
@Composable
fun PreviewAboutScreen() {
    AboutScreen(navController= NavController(LocalContext.current))
}