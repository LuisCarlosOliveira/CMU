package com.example.mysmarthome.ui.screens.phone

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.R
import com.example.mysmarthome.database.view_models.HomesViewModel
import com.example.mysmarthome.ui.components.FormStringTextField
import com.example.mysmarthome.qrCode.BarCodeAnalyser
import com.example.mysmarthome.ui.components.NormalButton
import com.example.mysmarthome.ui.components.TopBarBack
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AssociateHouseScreen(navController: NavController) {

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Open camera
        } else {
            // Show dialog
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val homesViewModel: HomesViewModel = viewModel(LocalContext.current as MainActivity)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        var nome by rememberSaveable {
            mutableStateOf("")
        }
        val localCtx = LocalContext.current

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBack(
                    title = stringResource(id = R.string.add_home),
                    navController = navController
                )
            },
            content = {
                Column(
                    Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    nome = FormStringTextField(
                        title = "Id da Casa",
                        placeholder = "Insira o Id da Casa",
                        label = "Id da Casa"
                    )
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    NormalButton(modifier = Modifier
                        .width(250.dp)
                        .height(80.dp),
                        action = {
                            homesViewModel.getAllFiestore(nome)
                            navController.navigate("HomePageScreen")
                            },
                        title = "Importar esta casa"
                    )
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
                    NormalButton(
                        modifier = Modifier
                            .width(250.dp)
                            .height(80.dp),
                        action = {
                            cameraPermissionState.launchPermissionRequest()
                            navController.navigate("CameraPreviewScreen")
                        },
                        title = stringResource(id = R.string.readQRCode)
                    )
                }
            }
        )
    }
}


@Preview()
@Composable
fun PreviewAssociateHomeScreen() {
    AssociateHouseScreen(navController = NavController(LocalContext.current))
}
