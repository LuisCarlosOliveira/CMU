package com.example.mysmarthome.ui.screens.tablet

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.QrCode2
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R

@Composable
fun InviteMemberScreen(mainActivity: MainActivity) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var titulo by rememberSaveable {
        mutableStateOf("")
    }
    var descricao by rememberSaveable {
        mutableStateOf("")
    }

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
                    modifier = Modifier.padding(top = 7.dp, start = 10.dp, end = 10.dp),
                    fontSize = 22.sp,
                    text = stringResource(id = R.string.inviteMemberTitle)
                )

                var dialogOpen by remember { mutableStateOf(false) }

                if (dialogOpen) {
                    AlertDialog(
                        onDismissRequest = { dialogOpen = false },
                        buttons = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                                    onClick = {
                                        dialogOpen = false
                                        mainActivity.composeEmail(
                                            arrayOf(email),
                                            titulo,
                                            descricao,
                                            Uri.EMPTY
                                        )
                                    }) {
                                    Text(color = Color.White, text = "Convidar")
                                }
                                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                                    onClick = { dialogOpen = false }) {
                                    Text(color = Color.White, text = "Cancelar")
                                }
                            }
                        },

                        title = { },

                        text = {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {

                                TextField(colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Blue,
                                    unfocusedIndicatorColor = Color.Blue,
                                    disabledIndicatorColor = Color.Blue
                                ),
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth(),
                                    value = email,
                                    shape = RoundedCornerShape(7.dp),
                                    onValueChange = { email = it },
                                    placeholder = { Text(text = "Insira o(s) Email(s)") },
                                    label = { Text(text = "Email(s)") })

                                TextField(colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Blue,
                                    unfocusedIndicatorColor = Color.Blue,
                                    disabledIndicatorColor = Color.Blue
                                ),
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 10.dp)
                                        .fillMaxWidth(),
                                    value = titulo,
                                    shape = RoundedCornerShape(7.dp),
                                    onValueChange = { titulo = it },
                                    placeholder = { Text(text = "Insira o  Título") },
                                    label = { Text(text = "Título") })

                                TextField(colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Blue,
                                    unfocusedIndicatorColor = Color.Blue,
                                    disabledIndicatorColor = Color.Blue
                                ),
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    value = descricao,
                                    shape = RoundedCornerShape(7.dp),
                                    maxLines = 3,
                                    onValueChange = { descricao = it },
                                    placeholder = { Text(text = "Insira a Descrição") },
                                    label = { Text(text = "Descrição") })

                                IconButton(
                                    onClick = { }
                                ) {
                                    Icon(
                                        Icons.Filled.UploadFile, "",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .width(50.dp)
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(2.dp),
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = Color.White,
                        properties = DialogProperties(
                            dismissOnBackPress = true,
                            dismissOnClickOutside = true
                        )
                    )
                }


                IconButton(
                    onClick = { dialogOpen = true }
                ) {
                    Icon(
                        Icons.Filled.Email, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 10.dp),
                    )
                }

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
                    .padding(top = 20.dp, bottom = 50.dp)
            ) {
                Image(
                    painterResource(id = mainActivity.imgQRCode()),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}