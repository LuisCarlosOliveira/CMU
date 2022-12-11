package com.example.mysmarthome.ui.screens.phone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mysmarthome.R

@Composable
fun AppImage() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "App Logo",
        modifier = Modifier.size(200.dp)
        //contentScale = ContentScale.Crop
    )
}


@Composable
fun LoginScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisibility by rememberSaveable {
            mutableStateOf(false)
        }

        AppImage()

        Text(
            text = stringResource(id = R.string.app_name),
            fontStyle = FontStyle.Italic,
            style = TextStyle(fontSize = 35.sp, fontFamily = FontFamily.SansSerif)
        )

        Spacer(modifier = Modifier.padding(25.dp))

        TextField(colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Blue
        ),
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                .width(300.dp),
            value = email,
            shape = RoundedCornerShape(7.dp),
            onValueChange = { email = it },
            placeholder = { Text(text = "Insira o Email") },
            label = { Text(text = "Email") })

        Spacer(modifier = Modifier.padding(20.dp))

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
                .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                .width(300.dp),
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(7.dp),
            placeholder = { Text(text = "Insira Password") },
            label = { Text(text = "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = icon, contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("HomePageScreen")
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = White,
                    text = "Iniciar Sessão"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("NewAccountScreen")
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = White,
                    text = "Criar Conta"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}