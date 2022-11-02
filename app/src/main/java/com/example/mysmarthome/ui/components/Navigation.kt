package com.example.mysmarthome.ui.components

import androidx.compose.runtime.Composable
import com.example.mysmarthome.MainActivity

@Composable
fun Navigation(mainActivity: MainActivity) {

/*
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "WelcomeScreen") {
        composable("WelcomeScreen") {
            WelcomeScreen(navController = navController)
        }
        composable("QuestionsScreen") {
            QuestionsScreen(mainActivity,questions, navController, onChange = { it ->
                for (q in questions) {
                    if (q.x == it.x && q.y == it.y) {
                        q.input = it.input
                    }
                }
            })
        }
        composable(
            "SingleQuestionScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            var Id = it.arguments?.getInt("id")!!
            question = questions[Id]
            SingleQuestionScreen(question, navController, onChange = {})
        }

    }
*/

}