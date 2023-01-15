package com.example.mysmarthome.ui.screens.phone

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.AddImage
import com.example.mysmarthome.ui.components.TopBarBackForward
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType


@Composable
fun InviteMemberScreen(mainActivity: MainActivity, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val ctx = LocalContext.current

        var hasFile by remember { mutableStateOf(false) }
        var fileUri by remember { mutableStateOf<Uri?>(null) }

        val filePicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                hasFile = uri != null
                fileUri = uri
            })

        var email by rememberSaveable {
            mutableStateOf("")
        }
        var titulo by rememberSaveable {
            mutableStateOf("")
        }
        var descricao by rememberSaveable {
            mutableStateOf("")
        }

        var dialogOpen by remember { mutableStateOf(false) }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBackForward(
                    title = stringResource(id = R.string.inviteMemberTitle),
                    actionBtns = {
                        IconButton(onClick = { dialogOpen = true }) {
                            Icon(Icons.Rounded.Email, "", tint = Color.Black)
                        }
                    },
                    navController = navController
                )
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
                                            fileUri ?: Uri.EMPTY
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
                                    onClick = { filePicker.launch("*/*") }
                                ) {
                                    Icon(
                                        Icons.Filled.UploadFile, "",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .width(50.dp)
                                    )
                                }
                                if (hasFile && fileUri != null) {
                                    Toast.makeText(
                                        ctx, "Ficheiro Selecionado com Sucesso", Toast
                                            .LENGTH_SHORT
                                    ).show()
                                } else {
                                    null
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
            },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, bottom = 50.dp)
                ) {
                    val URL = "Mostrar ao Rafa"
                    // Make sure the value is valid for the type of barcode selected. The library will
                    // just show an infinite spinner in place of a barcode if the data is not valid.
                    if (BarcodeType.QR_CODE.isValueValid(URL)) {
                        Barcode(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(400.dp)
                                .height(400.dp),
                            resolutionFactor = 20, // Optionally, increase the resolution of the generated image
                            type = BarcodeType.QR_CODE, // pick the type of barcode you want to render
                            value = URL // The textual representation of this code
                        )
                    }
                    // You must handle invalid data yourself
                    if (!BarcodeType.CODE_128.isValueValid(URL)) {
                        Text("this is not code 128 compatible")
                    }
                }
            }
        )
    }
}

@Preview()
@Composable
fun PreviewInviteMemberScreen() {
    InviteMemberScreen(
        mainActivity = MainActivity(),
        navController = NavController(LocalContext.current)
    )
}