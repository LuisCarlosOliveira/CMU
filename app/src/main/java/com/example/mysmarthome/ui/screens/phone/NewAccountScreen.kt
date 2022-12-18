package com.example.mysmarthome.ui.screens.phone

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.view_models.UsersViewModel
import com.example.mysmarthome.enums.TypeMember
import com.example.mysmarthome.ui.components.FormNumberTextField
import com.example.mysmarthome.ui.components.FormPasswordTextField
import com.example.mysmarthome.ui.components.FormStringTextField
import com.example.mysmarthome.ui.components.TopBarBackForward

@Composable
fun NewAccountScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val localCtx = LocalContext.current

        var nome by rememberSaveable {
            mutableStateOf("")
        }
        var contacto by rememberSaveable {
            mutableStateOf("")
        }
        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var password2 by rememberSaveable {
            mutableStateOf("")
        }

        val usersViewModel: UsersViewModel = viewModel()
        val users = usersViewModel.allUsers.observeAsState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBackForward(
                    iconForward = Icons.Rounded.ArrowForward,
                    title = stringResource(id = com.example.mysmarthome.R.string.newAccountTitle),
                    actionBack = { navController.popBackStack() },
                    actionForward = {
                        if (nome.isNotEmpty() && contacto.isNotEmpty() && email.isNotEmpty() &&
                            password.isNotEmpty() && password2.isNotEmpty()
                        ) {
                            if (contacto.length == 9 && password.equals(password2)) {
                                usersViewModel.insertUser(
                                    User(
                                        1, nome, email,
                                        TypeMember.Administrador, password, contacto.toInt()
                                    )
                                )
                                Toast.makeText(localCtx, "Conta Criada!", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate("ChooseTypeHomeScreen")
                            } else {
                                Toast.makeText(
                                    localCtx,
                                    "O Contacto tem de ter 9 números e as Passwords devem ser iguais!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } else {
                            Toast.makeText(
                                localCtx,
                                "Preencha todos os campos!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            },
            content = {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {

                    nome = FormStringTextField(
                        title = "Nome",
                        placeholder = "Insira o Nome",
                        label = "Nome"
                    )

                    contacto = FormNumberTextField(
                        title = "Contacto Telefónico",
                        placeholder = "Insira o Contacto",
                        label = "Contacto"
                    )

                    email = FormStringTextField(
                        title = "Email",
                        placeholder = "Insira o Email",
                        label = "Email"
                    )

                    password = FormPasswordTextField(
                        title = "Password",
                        placeholder = "Inserir Password",
                        label = "Password"
                    )

                    password2 = FormPasswordTextField(
                        title = "Repetir Password",
                        placeholder = "Repetir Password",
                        label = "Repetir Password"
                    )
                }
            }
        )
    }
}

@Preview()
@Composable
fun PreviewNewAccountScreen() {
    NewAccountScreen(navController= NavController(LocalContext.current))
}