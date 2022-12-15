package com.example.mysmarthome.ui.screens.phone

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.FloatingButton
import com.example.mysmarthome.ui.components.SimpleTextField
import com.example.mysmarthome.ui.components.TopbarBack

@Composable
fun NewDivisionScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        var divisionName by rememberSaveable {
            mutableStateOf("")
        }
        val result = remember { mutableStateOf<Bitmap?>(null) }
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                result.value = it
            }

        var hasImg by remember { mutableStateOf(false) }
        var imgUri by remember { mutableStateOf<Uri?>(null) }

        val imgPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                hasImg = uri != null
                imgUri = uri
            })

        var letterSpacing by remember {
            mutableStateOf(1.sp)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopbarBack(
                    title = stringResource(id = R.string.newDivisionTitle),
                    navController = navController
                )
            },

            content = {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    divisionName = SimpleTextField(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                        "Inserir Nova Divisão", "Divisão"
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(start = 20.dp, end = 20.dp)
                            .border(
                                border =
                                BorderStroke(width = 1.dp, Color.LightGray)
                            ), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            fontWeight = FontWeight.Medium,
                            letterSpacing = letterSpacing,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 16.sp,
                            text = "Imagem da Divisão: "
                        )

                        IconButton(
                            onClick = {
                                launcher.launch()
                            }
                        ) {
                            Icon(
                                Icons.Rounded.AddAPhoto, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(30.dp),
                            )
                        }

                        IconButton(
                            onClick = {
                                imgPicker.launch("image/*")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.ImageSearch, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(30.dp),
                            )
                        }
                    }
                    result.value?.let { image ->
                        addImage(image.asImageBitmap())
                    }

                    if (hasImg && imgUri != null) {
                        AsyncImage(
                            model = imgUri,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp, start = 20.dp, bottom = 90.dp, end = 20.dp),
                            contentScale = ContentScale.FillWidth, contentDescription = ""
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingButton(
                    icon = Icons.Rounded.DevicesOther,
                    title = stringResource(id = R.string.associateDevicesBtn),
                    action = { navController.navigate("UnconnectedDevicesScreen") })
            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Composable
fun addImage(imageBitmap: ImageBitmap?) {
    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap, modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 20.dp, bottom = 90.dp, end = 20.dp),
            contentScale = ContentScale.FillWidth,
            contentDescription = ""
        )
    }
}

@Preview()
@Composable
fun PreviewNewDivisionScreen() {
    NewDivisionScreen(navController = NavController(LocalContext.current))
}