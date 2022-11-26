package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R

@Composable
fun ChooseTypeHomeScreen(mainActivity: MainActivity) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column( modifier = Modifier.fillMaxSize(),
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
                            modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                            fontSize = 22.sp,
                            text = "Casa"
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
                    Column(Modifier.fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                            border = BorderStroke(1.dp, Color.Blue),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.width(250.dp).height(80.dp),
                            onClick = { /*TODO*/ }) {
                            Text(text = stringResource(id = R.string.newHomeTitle),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium)
                        }

                        Spacer(Modifier.padding(60.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                            border = BorderStroke(1.dp, Color.Blue),
                            shape = RoundedCornerShape(20.dp),modifier = Modifier.width(250.dp).height(80.dp),
                            onClick = { /*TODO*/ }) {
                            Text(text = stringResource(id = R.string.add_home),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium)
                        }
                    }

                },
            )

        }
    }
}

