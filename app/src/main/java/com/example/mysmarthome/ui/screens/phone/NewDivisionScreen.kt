package com.example.mysmarthome.ui.screens.phone

import AppImage
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R

@Composable
fun NewDivisionScreen(/*mainActivity: MainActivity ,navController: NavController*/) {
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
                        modifier = Modifier.padding(top = 7.dp, end = 150.dp),
                        fontSize = 22.sp,
                        text = "Nova Divisão"
                    )

                    var dialogOpen by remember { mutableStateOf(false) }



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


                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, bottom = 50.dp, start = 20.dp, end = 20.dp)
                ) {
                    OutLineTextField()
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp, bottom = 50.dp)){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(start = 10.dp, end = 20.dp)
                            .border(
                                border =
                                BorderStroke(width = 1.dp, Color.LightGray)
                            ), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            fontWeight = FontWeight.Medium,
                            letterSpacing = letterSpacing,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 15.dp, end = 150.dp, start = 22.dp),
                            fontSize = 14.sp,
                            text = "Imagem da Divisão"
                        )
                        IconButton(
                            onClick = { }//dialogOpen = true }
                        ) {
                            Icon(
                                Icons.Rounded.AddAPhoto, "",
                                tint = Color.Black,
                                modifier = Modifier
                                        .width(50.dp)
                                    .size(40.dp)
                                    .padding(end = 20.dp),
                            )
                        }
                        IconButton(
                            onClick = { }//dialogOpen = true }
                        ) {
                            Icon(
                                Icons.Rounded.ImageSearch , "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .width(100.dp)
                                    .size(40.dp)
                                    .padding(end = 100.dp),
                            )
                        }
                    }
                }


                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 250.dp, bottom = 50.dp)
                ) {

                    addImage()
                }

            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            Icons.Rounded.DevicesOther, "",
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )
                    },
                    text = { Text("Associar Dispositivos", fontSize = 18.sp) },
                    backgroundColor = Color.Gray,
                    onClick = { },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                )

            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Composable
fun OutLineTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        label = { Text(text = "Nova Divisão") },
        placeholder = { Text(text = "Inserir Nova Divisão") },
        onValueChange = {
            text = it
        }
    )
}

@Composable
fun addImage(){
    Image(
        painter = painterResource(id = R.drawable.insert_picture),
        contentDescription = "insert Picture",
        //contentScale = ContentScale.Crop

    )
}

@Preview()
@Composable
fun PreviewNewDivisionScreen() {
    NewDivisionScreen()
}