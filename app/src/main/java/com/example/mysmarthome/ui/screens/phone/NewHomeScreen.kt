package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.FloatingButton
import com.example.mysmarthome.ui.components.FormStringTextField
import com.example.mysmarthome.ui.components.SimpleTextField
import com.example.mysmarthome.ui.components.TopbarBack

@Composable
fun NewHomeScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        var nome by rememberSaveable {
            mutableStateOf("")
        }

        var street by rememberSaveable {
            mutableStateOf("")
        }

        var postalcode by rememberSaveable {
            mutableStateOf("")
        }

        var city by rememberSaveable {
            mutableStateOf("")
        }

        var country by rememberSaveable {
            mutableStateOf("")
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopbarBack(
                    title = stringResource(id = R.string.newHomeTitle),
                    navController = navController
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    nome = FormStringTextField("Nome", "Insira o Nome da Casa", "Nome da Casa")

                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Morada"
                    )

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .background(Color.LightGray)
                    ) {

                        street = SimpleTextField(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(), placeholder = "Insira a Rua", label = "Rua"
                        )

                        postalcode = SimpleTextField(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            placeholder = "Insira o Código Postal",
                            label = "Código Postal"
                        )

                        city = SimpleTextField(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                                .fillMaxWidth(), placeholder = "Insira a Cidade", label = "Cidade"
                        )

                        country = SimpleTextField(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
                                .fillMaxWidth(), placeholder = "Insira o País", label = "País"
                        )

                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FloatingButton(
                            icon = Icons.Rounded.ArrowForward,
                            title = stringResource(id = R.string.continueBtn),
                            action = { navController.navigate("NewDivisionScreen") })
                    }
                }
            },
        )
    }
}

@Preview()
@Composable
fun PreviewNewHomeScreen() {
    NewHomeScreen(navController = NavController(LocalContext.current))
}