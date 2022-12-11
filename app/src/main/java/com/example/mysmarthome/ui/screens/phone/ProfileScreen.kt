package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.mysmarthome.R

@Composable
fun ProfileScreen(navController: NavController) {
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

            var letterSpacing by remember {
                mutableStateOf(1.sp)
            }

            var email by rememberSaveable {
                mutableStateOf("")
            }

            var password by rememberSaveable {
                mutableStateOf("")
            }

            var repeatedpassword by rememberSaveable {
                mutableStateOf("")
            }

            var passwordVisibility by rememberSaveable {
                mutableStateOf(false)
            }

            var repeatedPasswordVisibility by rememberSaveable {
                mutableStateOf(false)
            }

            var dialogOpenEmail by remember { mutableStateOf(false) }

            var dialogOpenPassword by remember { mutableStateOf(false) }

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    Row(
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
                            modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                            fontSize = 22.sp,
                            text = "João " + stringResource(id = R.string.admin)
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
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                            .padding(top = 20.dp)
                    ) {

                        val options: Array<String> = stringArrayResource(id = R.array.profile)

                        options.forEach { opt ->

                            if (opt.equals("Alterar Email")) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(90.dp)
                                        .padding(start = 20.dp, end = 20.dp)
                                        .clickable(
                                            onClick = { dialogOpenEmail = true })
                                        .border(
                                            border =
                                            BorderStroke(width = 1.dp, Color.LightGray)
                                        ), verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        fontWeight = FontWeight.Medium,
                                        letterSpacing = letterSpacing,
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                        fontSize = 18.sp,
                                        text = opt
                                    )
                                    if (dialogOpenEmail) {
                                        AlertDialog(
                                            onDismissRequest = { dialogOpenEmail = false },
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
                                                            dialogOpenEmail = false
                                                        }) {
                                                        Text(color = Color.White, text = "Alterar")
                                                    }
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = { dialogOpenEmail = false }) {
                                                        Text(color = Color.White, text = "Cancelar")
                                                    }
                                                }
                                            },

                                            title = {

                                            },

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
                                                            .padding(top = 30.dp)
                                                            .fillMaxWidth(),
                                                        value = email,
                                                        shape = RoundedCornerShape(7.dp),
                                                        onValueChange = { email = it },
                                                        placeholder = { Text(text = "Insira Novo Email") },
                                                        label = { Text(text = "Email") })

                                                }
                                            },

                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(250.dp)
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
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Email, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 15.dp),
                                        )
                                    }
                                }
                            } else if (opt.equals("Alterar Password")) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(90.dp)
                                        .padding(start = 20.dp, end = 20.dp)
                                        .clickable(
                                            onClick = { dialogOpenPassword = true })
                                        .border(
                                            border =
                                            BorderStroke(width = 1.dp, Color.LightGray)
                                        ), verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        fontWeight = FontWeight.Medium,
                                        letterSpacing = letterSpacing,
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                        fontSize = 18.sp,
                                        text = opt
                                    )

                                    if (dialogOpenPassword) {
                                        AlertDialog(
                                            onDismissRequest = { dialogOpenPassword = false },
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
                                                            dialogOpenPassword = false
                                                        }) {
                                                        Text(color = Color.White, text = "Alterar")
                                                    }
                                                    Button(colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Blue
                                                    ),
                                                        onClick = { dialogOpenPassword = false }) {
                                                        Text(color = Color.White, text = "Cancelar")
                                                    }
                                                }
                                            },

                                            title = {

                                            },

                                            text = {
                                                Column(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight()
                                                ) {

                                                    val icon = if (passwordVisibility)
                                                        painterResource(id = android.R.drawable.ic_partial_secure)
                                                    else
                                                        painterResource(id = android.R.drawable.ic_secure)

                                                    TextField(
                                                        colors = TextFieldDefaults.textFieldColors(
                                                            focusedIndicatorColor = Color.Blue,
                                                            unfocusedIndicatorColor = Color.Blue,
                                                            disabledIndicatorColor = Color.Blue
                                                        ),
                                                        modifier = Modifier
                                                            .padding(
                                                                start = 20.dp,
                                                                top = 10.dp,
                                                                end = 20.dp
                                                            )
                                                            .fillMaxWidth(),
                                                        value = password,
                                                        onValueChange = { password = it },
                                                        shape = RoundedCornerShape(7.dp),
                                                        placeholder = { Text(text = "Password") },
                                                        label = { Text(text = "Nova Password") },
                                                        trailingIcon = {
                                                            IconButton(onClick = {
                                                                passwordVisibility =
                                                                    !passwordVisibility
                                                            }) {
                                                                Icon(
                                                                    painter = icon,
                                                                    contentDescription = ""
                                                                )
                                                            }
                                                        },
                                                        keyboardOptions = KeyboardOptions(
                                                            keyboardType = KeyboardType.Password
                                                        ),
                                                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                                                    )
                                                    Spacer(modifier = Modifier.padding(top = 20.dp))
                                                    TextField(
                                                        colors = TextFieldDefaults.textFieldColors(
                                                            focusedIndicatorColor = Color.Blue,
                                                            unfocusedIndicatorColor = Color.Blue,
                                                            disabledIndicatorColor = Color.Blue
                                                        ),
                                                        modifier = Modifier
                                                            .padding(
                                                                start = 20.dp,
                                                                top = 10.dp,
                                                                end = 20.dp
                                                            )
                                                            .fillMaxWidth(),
                                                        value = repeatedpassword,
                                                        onValueChange = { repeatedpassword = it },
                                                        shape = RoundedCornerShape(7.dp),
                                                        placeholder = { Text(text = "Repetir Password") },
                                                        label = { Text(text = "Password") },
                                                        trailingIcon = {
                                                            IconButton(onClick = {
                                                                repeatedPasswordVisibility =
                                                                    !repeatedPasswordVisibility
                                                            }) {
                                                                Icon(
                                                                    painter = icon,
                                                                    contentDescription = ""
                                                                )
                                                            }
                                                        },
                                                        keyboardOptions = KeyboardOptions(
                                                            keyboardType = KeyboardType.Password
                                                        ),
                                                        visualTransformation = if (repeatedPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                                                    )

                                                }
                                            },

                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(250.dp)
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
                                        onClick = { }
                                    ) {
                                        Icon(
                                            Icons.Rounded.Password, "",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(end = 15.dp),
                                        )
                                    }
                                }

                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(90.dp)
                                        .padding(start = 20.dp, end = 20.dp)
                                        .border(
                                            border = BorderStroke(
                                                width = 1.dp,
                                                Color.LightGray
                                            )
                                        )
                                        .clickable(
                                            onClick = {
                                                when (opt) {
                                                    "Pedidos de Adesão" -> navController.navigate("MemberRequestScreen")
                                                    "Configurações Pessoais" -> navController.navigate(
                                                        "PersonalConfigsScreen"
                                                    )
                                                    "Membros" -> navController.navigate("MembersScreen")
                                                }
                                            },
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        fontWeight = FontWeight.Medium,
                                        letterSpacing = letterSpacing,
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                        fontSize = 18.sp,
                                        text = opt
                                    )
                                    if (opt.equals("Pedidos de Adesão")) {
                                        IconButton(
                                            onClick = { }
                                        ) {
                                            Icon(
                                                Icons.Rounded.PriorityHigh, "",
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .padding(end = 15.dp),
                                            )
                                        }
                                    } else if (opt.equals("Configurações Pessoais")) {
                                        IconButton(
                                            onClick = { }
                                        ) {
                                            Icon(
                                                Icons.Rounded.Star, "",
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .padding(end = 15.dp),
                                            )
                                        }
                                    } else {
                                        IconButton(
                                            onClick = { }
                                        ) {
                                            Icon(
                                                Icons.Rounded.Group, "",
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .padding(end = 15.dp),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}