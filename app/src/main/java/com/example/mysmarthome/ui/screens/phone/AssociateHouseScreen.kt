package com.example.mysmarthome.ui.screens.phone

import android.Manifest
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.mysmarthome.R
import com.example.mysmarthome.qrCode.BarCodeAnalyser
import com.example.mysmarthome.ui.components.NormalButton
import com.example.mysmarthome.ui.components.TopBarBack
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AssociateHouseScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBarBack(
                    title = stringResource(id = R.string.add_home),
                    navController = navController
                )
            }
        ) {
            Column(
                Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

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
    }
}


@Preview()
@Composable
fun PreviewAssociateHomeScreen() {
    AssociateHouseScreen(navController = NavController(LocalContext.current))
}