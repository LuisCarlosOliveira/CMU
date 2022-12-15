package com.example.mysmarthome.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mysmarthome.MainActivity
import com.example.mysmarthome.ui.screens.phone.*

@Composable
fun Navigation(mainActivity: MainActivity) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen( navController = navController)
        }
        composable("HomePageScreen") {
            HomePageScreen(mainActivity, navController=  navController)
        }
        composable("ConnectedDevicesScreen") {
            ConnectedDevicesScreen( navController=  navController)
        }
        composable("ProfileScreen") {
            ProfileScreen( navController=  navController)
        }
        composable("ConsumptionsScreen") {
            ConsumptionsScreen( navController=  navController)
        }
        composable("DefinitionsScreen") {
            DefinitionsScreen( navController=  navController)
        }
        composable("NewAccountScreen") {
            NewAccountScreen( navController=  navController)
        }
        composable("ChooseTypeHomeScreen") {
            ChooseTypeHomeScreen( navController=  navController)
        }
        composable("NewHomeScreen") {
            NewHomeScreen( navController=  navController)
        }
        composable("NewDivisionScreen") {
            NewDivisionScreen( navController=  navController)
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
        composable("DivisionDetailsScreen") {
            DivisionDetailsScreen( mainActivity, navController=  navController)
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
        composable("DefinitionsDivisionsDevicesScreen") {
            DefinitionsDivisionsDevicesScreen( navController=  navController)
        }
        composable("LocationScreen") {
            LocationScreen( navController=  navController)
        }
        composable("AssociateHouseScreen") {
            AssociateHouseScreen( navController=  navController)
        }

        /*
        composable(
            "SingleQuestionScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            var Id = it.arguments?.getInt("id")!!
            question = questions[Id]
            SingleQuestionScreen(question, navController, onChange = {})
        }
        */

    }
}