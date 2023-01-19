package com.example.mysmarthome.ui.screens.phone

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.view_models.HomesViewModel
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
        val homesViewModel: HomesViewModel = viewModel(LocalContext.current as MainActivity)
        val home = homesViewModel.home.observeAsState()
        LaunchedEffect(Unit){homesViewModel.getFirstHome()}

        val usersViewModel: UsersViewModel = viewModel(LocalContext.current as MainActivity)
        val user = usersViewModel.user.observeAsState()

        var selectedTab by remember { mutableStateOf(0) }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBackForward(
                    title = stringResource(id = com.example.mysmarthome.R.string.newAccountTitle),
                    actionBtns = {
                        IconButton(onClick = { selectedTab = 1 }) {
                            Icon(Icons.Rounded.ArrowForward, "", tint = Color.Black)
                        }
                    },
                    navController = navController
                )
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
        when (selectedTab) {
            1 -> {
                if (nome.isNotEmpty() && contacto.isNotEmpty() && email.isNotEmpty() &&
                    password.isNotEmpty() && password2.isNotEmpty()
                ) {
                    if (password.equals(password2) && contacto.length == 9 && password.length >= 6) {
                        LaunchedEffect(Unit) {
                            usersViewModel.insertUser(
                                User(nome, email, "Administrador", password, contacto.toInt(),0)
                            )
                        }
                        usersViewModel.register(email, password)
                        usersViewModel.getUserByEmail(email)
                        if (user.value != null) {
                            Toast.makeText(localCtx,"Conta Criada!",Toast.LENGTH_SHORT).show()
                            if (home.value != null) {
                                navController.navigate("HomePageScreen")
                            } else {
                                navController.navigate("ChooseTypeHomeScreen")
                            }
                            selectedTab = 0
                        }
                    } else {
                        Toast.makeText(localCtx,"O Contacto tem de ter 9 números, a password deve ter mais que 6 caracteres e as Passwords devem ser iguais!", Toast.LENGTH_SHORT).show()
                        selectedTab = 0
                    }
                } else {
                    Toast.makeText(localCtx, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                    selectedTab = 0
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewNewAccountScreen() {
    NewAccountScreen(navController = NavController(LocalContext.current))
}