package com.example.mysmarthome.ui.screens.phone

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.entities.Address
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.view_models.HomesViewModel
import com.example.mysmarthome.database.view_models.UsersViewModel
import com.example.mysmarthome.ui.components.*

@Composable
fun NewHomeScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val homesViewModel: HomesViewModel = viewModel()

        val localCtx = LocalContext.current
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
        var idSecret by rememberSaveable {
            mutableStateOf("")
        }


        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBack(
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

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    idSecret = FormStringTextField("ID ", "Insira o id para interligar com a firestore", "Id da Casa para interligar com a firestore")

                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                        fontWeight = FontWeight.Bold,
                        text = "Morada"
                    )

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            //.background(Color.LightGray)
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
                    Column(Modifier.padding(top = 35.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            FloatingButton(
                                icon = Icons.Rounded.ArrowForward,
                                title = stringResource(id = R.string.continueBtn),
                                action = {
                                    if (street.isNotEmpty() && postalcode.isNotEmpty()
                                        && city.isNotEmpty() && country.isNotEmpty() &&
                                        nome.isNotEmpty()&& idSecret.isNotEmpty() && idSecret.length > 6) {
                                        val address = Address(street, postalcode, city, country)
                                        homesViewModel.insertHome(Home(address,nome, idSecret))
                                        navController.navigate("NewDivisionScreen")
                                    } else {
                                        Toast.makeText(localCtx,"Preencha todos os campos!\nTenha em atenção que o Id deve ter mais que 6 caracteres",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }
                    }

                }

            }
        )
    }
}