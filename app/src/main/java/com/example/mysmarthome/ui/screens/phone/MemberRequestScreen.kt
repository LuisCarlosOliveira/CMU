package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.ui.components.BottombarWithHome
import com.example.mysmarthome.ui.components.DropDownMenuOutlined
import com.example.mysmarthome.ui.components.TopBarBack

@Composable
fun MemberRequestScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var letterSpacing by remember {
        mutableStateOf(1.sp)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarBack(
                title = "João " + stringResource(id = R.string.adesaoPedidos),
                navController = navController
            )
        },
        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 50.dp)
            ) {
                LazyColumn {
                    items(15) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
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
                            DropDownMenuOutlined(
                                Modifier
                                    .width(300.dp)
                                    .padding(end = 20.dp),
                                Modifier.padding(top = 50.dp),
                                options = stringArrayResource(
                                    id = R.array.membersType
                                )
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottombarWithHome(navController = navController)
        }
    )
}

@Preview()
@Composable
fun PreviewMemberRequestScreen() {
    MemberRequestScreen(navController = NavController(LocalContext.current))
}