package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.HomesViewModel
import com.example.mysmarthome.ui.components.AlertPopup
import com.example.mysmarthome.ui.components.TopBarBack

@Composable
fun DefinitionsScreen(navController: NavController) {

    val nocturnMode = remember { mutableStateOf(true) }
    val savingEnergyMode = remember { mutableStateOf(true) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val homesViewModel: HomesViewModel = viewModel(LocalContext.current as MainActivity)
    val home = homesViewModel.home.observeAsState()

    var dialogOpen by remember { mutableStateOf(false) }

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            TopBarBack(
                title = stringResource(id = R.string.definitionsTitle),
                navController = navController
            )
        },
        content = {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 10.dp)
            ) {

                val definitions: Array<String> = stringArrayResource(id = R.array.definitions)

                definitions.forEach { definition ->

                    if (definition.equals("Modo Noturno")) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
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
                                text = definition
                            )
                            Switch(
                                checked = nocturnMode.value,
                                onCheckedChange = { nocturnMode.value = it },
                                modifier = Modifier.padding(end = 20.dp)
                            )
                        }

                    } else if (definition.equals("Modo Poupar Energia")) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(border = BorderStroke(width = 1.dp, Color.LightGray)),
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
                                text = definition
                            )
                            Switch(
                                checked = savingEnergyMode.value,
                                onCheckedChange = { savingEnergyMode.value = it },
                                modifier = Modifier.padding(end = 20.dp)
                            )
                        }

                    } else {
                        AlertPopup(
                            state = dialogOpen,
                            btn1Text = "Sim", btn2Text = "Não",
                            content = {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        text = "Pretende Verdadeiramente Eliminar esta Casa?\n\n\nAo eliminar a casa irá ser redirecionado para a pagina de criar uma nova casa."
                                    )
                                }
                            },
                            actionBtn = {
                                homesViewModel.removeHome()
                                navController.navigate("NewHomeScreen")
                            },
                            actionBtn2 = { dialogOpen = false }
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(start = 20.dp, end = 20.dp)
                                .border(border = BorderStroke(width = 1.dp, Color.LightGray))
                                .clickable(
                                    onClick = {
                                        when (definition) {
                                            "Assistente Pessoal" -> navController.navigate("VirtualPersonalAssistantScreen")
                                            "Eliminar Casa" -> dialogOpen = true
                                            "Ajuda" -> navController.navigate("HelpScreen")
                                            "Sobre" -> navController.navigate("AboutScreen")
                                        }
                                    },
                                ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                fontWeight = FontWeight.Medium,
                                letterSpacing = letterSpacing,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 7.dp, start = 20.dp),
                                fontSize = 18.sp,
                                text = definition
                            )
                        }
                    }
                }
            }
        },
    )
}

@Preview()
@Composable
fun PreviewDefinitionsScreen() {
    DefinitionsScreen(navController = NavController(LocalContext.current))
}