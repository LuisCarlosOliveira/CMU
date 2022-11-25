package com.example.mysmarthome.ui.screens.tablet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun AboutScreen() {

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
                    modifier = Modifier.padding(top = 7.dp, start = 10.dp, end = 10.dp),
                    fontSize = 22.sp,
                    text = "Sobre"
                )

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        Icons.Filled.Help, "",
                        tint = Color.Black,
                        modifier = Modifier
                            .width(50.dp)
                            .padding(end = 5.dp),
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, top = 50.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                    )
                }

                Text(
                    letterSpacing = letterSpacing,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 50.dp, start = 15.dp),
                    fontSize = 18.sp,
                    text =
                    "Este Trabalho foi proposto ao grupo pelo Docente Fábio Silva, no âmbito " +
                            "da Unidade Curricular de Computação Móvel e Ubíqua.\n\n" +
                            "O projeto MySmartHome foi idealizado, tendo como objetivo facilitar a gestão" +
                            " de uma Casa Inteligente recorrendo a dispositivos móveis, tais como: " +
                            "Telemóvel e Tablet.\n\n" +
                            "Tendo isso em conta, na presente aplicação está disponível um vasto leque de funcionalidades " +
                            "que vão desde a criação de uma casa , passando pelo convite de " +
                            "membros (residentes ou não) até à interação direta com dispositivos conectados no sistema.\n\n " +
                            "O grupo que concebeu e idealizou este projeto, é composto pelos elementos:\n\n" +
                            "- Luís Oliveira; \n" +
                            "- Rafael Costa; \n" +
                            "- Teresa Peixoto. \n "
                )
            }
        },
    )
}