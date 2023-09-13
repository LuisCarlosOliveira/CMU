package com.example.mysmarthome.ui.components

import CameraPreviewScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.ui.screens.phone.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(mainActivity: MainActivity) {

    val navController = rememberNavController()
    var dataStoreUtil = mainActivity.dataStoreUtil
    var themeViewModel = mainActivity.themeViewModel

    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen( navController = navController)
        }
        composable("HomePageScreen") {
            HomePageScreen(mainActivity, navController=  navController)
        }
        composable("ConnectedDevicesScreen" ) {

            ConnectedDevicesScreen( navController=  navController)
        }
        composable("ProfileScreen" ) {
            ProfileScreen( navController=  navController)
        }
        composable("ConsumptionsScreen") {
            ConsumptionsScreen(mainActivity, navController=  navController)
        }
        composable("DefinitionsScreen") {
            DefinitionsScreen( navController=  navController, dataStoreUtil, themeViewModel)
        }
        composable("NewAccountScreen") {
            NewAccountScreen( navController=  navController)
        }
        composable("ChooseTypeHomeScreen") {
            ChooseTypeHomeScreen( navController=  navController)
        }
        composable("NewHomeScreen") {
            NewHomeScreen( navController=  navController )
        }
        composable("NewDivisionScreen") {
            NewDivisionScreen( navController=  navController )
        }
        composable("NewDeviceScreen") {
            NewDeviceScreen( navController=  navController)
        }
        composable("UnconnectedDevicesScreen") {
            UnconnectedDevicesScreen(mainActivity ,navController=  navController)
        }
        composable("MemberRequestScreen") {
            MemberRequestScreen( navController=  navController)
        }
        composable("PersonalConfigsScreen") {
            PersonalConfigsScreen( navController=  navController)
        }
        composable("MembersScreen") {
            MembersScreen( mainActivity, navController=  navController)
        }
        composable("InviteMemberScreen") {
            InviteMemberScreen( mainActivity, navController=  navController)
        }
        composable("DivisionDetailsScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            var Id = it.arguments?.getInt("id")!!
            DivisionDetailsScreen(navController=  navController, Id )
        }
        composable("VirtualPersonalAssistantScreen") {
            VirtualPersonalAssistantScreen( navController=  navController)
        }
        composable("HelpScreen") {
            HelpScreen( navController=  navController)
        }
        composable("AboutScreen") {
            AboutScreen( navController=  navController)
        }
        composable("LightScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            var Id = it.arguments?.getInt("id")!!
            LightScreen(navController=  navController, Id )
        }
        composable("PlugScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            var Id = it.arguments?.getInt("id")!!
            PlugScreen(navController=  navController, Id )
        }
        composable("BlindScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            var Id = it.arguments?.getInt("id")!!
            BlindScreen(navController=  navController, Id )
        }
        composable("LocationScreen") {
            LocationScreen( navController=  navController)
        }
        composable("AssociateHouseScreen") {
            AssociateHouseScreen( navController=  navController)
        }
        composable("CameraPreviewScreen") {
            CameraPreviewScreen( navController=  navController)
        }
    }
}