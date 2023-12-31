package com.example.mysmarthome.ui.screens.phone

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.HomesViewModel
import com.example.mysmarthome.database.view_models.UsersViewModel
import com.example.mysmarthome.ui.components.AddImage
import com.example.mysmarthome.ui.components.LoginSigninButton
import com.example.mysmarthome.ui.components.SimplePasswordTextField
import com.example.mysmarthome.ui.components.SimpleTextField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(navController: NavController) {
    var email by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}
    val usersViewModel: UsersViewModel = viewModel(LocalContext.current as MainActivity)
    val user = usersViewModel.user.observeAsState()
    val localCtx = LocalContext.current
    val homesViewModel: HomesViewModel = viewModel(LocalContext.current as MainActivity)
    val home = homesViewModel.home.observeAsState()
    LaunchedEffect(Unit){homesViewModel.getFirstHome()}

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddImage(Modifier.size(200.dp), painterResource(id = R.drawable.logo))
        Text(
            text = stringResource(id = R.string.app_name),
            fontStyle = FontStyle.Italic,
            style = TextStyle(fontSize = 35.sp, fontFamily = FontFamily.SansSerif)
        )
        Spacer(modifier = Modifier.padding(25.dp))
        email = SimpleTextField(
            Modifier
                .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                .width(300.dp),
            stringResource(id = R.string.insertEmail),
            stringResource(R.string.email)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        password = SimplePasswordTextField(
            Modifier
                .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                .width(300.dp),
            placeholder = stringResource(id = R.string.insertPassword),
            label = stringResource(id = R.string.password)
        )
        Spacer(modifier = Modifier.padding(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            LoginSigninButton(
                stringResource(id = R.string.login),
                action = {
                    usersViewModel.login(email, password)
                    if (user.value != null) {
                        Toast.makeText(
                            localCtx,
                            "Sucesso ao autenticar!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (home.value != null) {
                            navController.navigate("HomePageScreen")
                        } else {
                            navController.navigate("ChooseTypeHomeScreen")
                        }
                    }else{
                        Toast.makeText(
                            localCtx,
                            "Ocorreu algum erro ao autenticar!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )

        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            LoginSigninButton(
                stringResource(id = R.string.signin),
                action = { navController.navigate("NewAccountScreen") })
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}
