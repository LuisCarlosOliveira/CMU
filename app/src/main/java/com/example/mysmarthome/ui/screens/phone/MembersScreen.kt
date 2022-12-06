package com.example.mysmarthome.ui.screens.phone

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R

@Composable
fun MembersScreen(mainActivity: MainActivity) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp

            var message by rememberSaveable {
                mutableStateOf("")
            }

            var contact by rememberSaveable {
                mutableStateOf("912852300")
            }

            val ctx = LocalContext.current

            var hasFile by remember { mutableStateOf(false) }
            var fileUri by remember { mutableStateOf<Uri?>(null) }

            val filePicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri ->
                    hasFile = uri != null
                    fileUri = uri
                })

            var letterSpacing by remember {
                mutableStateOf(1.sp)
            }

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
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
                                    .padding(start = 5.dp)
                            )
                        }
                        Text(
                            fontWeight = FontWeight.Medium,
                            letterSpacing = letterSpacing,
                            fontFamily = FontFamily.Serif,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 7.dp),
                            fontSize = 22.sp,
                            text = "João " + stringResource(id = R.string.members)
                        )
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(
                                Icons.Rounded.PersonAddAlt, "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .width(50.dp)
                                    .padding(end = 5.dp)
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
                    Column(Modifier.padding(bottom = 60.dp)){
                    LazyColumn {
                        items(4) {
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 10.dp)
                            ) {
                                Text(
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = letterSpacing,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 7.dp, start = 20.dp)
                                        .width(screenWidth / 3),
                                    fontSize = 18.sp,
                                    text = "Maria"
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    dropDownMenuMembers2()


                                    IconButton(
                                        onClick = {
                                            mainActivity.callSomeone(contact)
                                        }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Phone, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(55.dp)
                                                .padding(top = 35.dp, end = 15.dp)
                                        )
                                    }

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
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = {
                                                            dialogOpen = false
                                                            mainActivity.composeSMSMessage(
                                                                message,
                                                                contact,
                                                                fileUri ?: Uri.EMPTY
                                                            )
                                                        }) {
                                                        Text(color = Color.White, text = "Enviar")
                                                    }
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
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

                                                    Text(
                                                        fontFamily = FontFamily.SansSerif,
                                                        fontWeight = FontWeight.Medium,
                                                        color = Color.Black,
                                                        fontSize = 16.sp,
                                                        letterSpacing = 1.sp,
                                                        text = "Inserir Mensagem: "
                                                    )

                                                    TextField(colors = TextFieldDefaults.textFieldColors(
                                                        focusedIndicatorColor = Color.Blue,
                                                        unfocusedIndicatorColor = Color.Blue,
                                                        disabledIndicatorColor = Color.Blue
                                                    ),
                                                        modifier = Modifier
                                                            .padding(top = 10.dp, bottom = 10.dp)
                                                            .fillMaxWidth()
                                                            .height(120.dp),
                                                        value = message,
                                                        shape = RoundedCornerShape(7.dp),
                                                        maxLines = 3,
                                                        onValueChange = { message = it },
                                                        placeholder = { Text(text = "Insira a Mensagem") },
                                                        label = { Text(text = "Mensagem") })

                                                    Text(
                                                        fontFamily = FontFamily.SansSerif,
                                                        fontWeight = FontWeight.Medium,
                                                        color = Color.Black,
                                                        fontSize = 16.sp,
                                                        letterSpacing = 1.sp,
                                                        text = "Anexar Ficheiro: "
                                                    )

                                                    IconButton(
                                                        onClick = {
                                                            filePicker.launch("*/*")
                                                        }
                                                    ) {
                                                        Icon(
                                                            Icons.Filled.UploadFile, "",
                                                            tint = Color.Black,
                                                            modifier = Modifier
                                                                .width(50.dp)
                                                                .padding(top = 10.dp)
                                                        )
                                                    }
                                                    if (hasFile && fileUri != null) {
                                                        Toast.makeText(
                                                            ctx,
                                                            "Ficheiro Selecionado com Sucesso",
                                                            Toast
                                                                .LENGTH_SHORT
                                                        ).show()
                                                    } else {
                                                        null
                                                    }

                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(350.dp)
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
                                            Icons.Rounded.Sms, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(55.dp)
                                                .padding(top = 35.dp, end = 15.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.DeleteForever, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(65.dp)
                                                .padding(top = 35.dp, end = 15.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxWidth()
                            .height(60.dp), horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(
                            onClick = { }
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
                            onClick = { }
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
                            onClick = { }
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
                            onClick = { }
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
            )
        }
    }
}


@SuppressLint("ResourceType")
@Composable
fun dropDownMenuMembers2() {
    var expanded by remember { mutableStateOf(false) }
    val memberType: Array<String> = stringArrayResource(id = R.array.membersType)
    val select: String = memberType[0]
    var selectedText by remember { mutableStateOf(select) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val localization = if (selectedText.equals("Residente Infantil"))
        Icons.Filled.LocationOn
    else
        null

    Column(Modifier.padding(start = 20.dp, top = 15.dp)) {
        Row() {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .width(180.dp)
                    .padding(end = 20.dp),
                label = { Text(stringResource(id = R.string.memberType)) },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(220.dp)
            ) {
                memberType.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
            IconButton(
                onClick = { }
            ) {
                if (localization != null) {
                    Icon(
                        localization, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 20.dp)
                    )
                }
            }
        }
    }
}