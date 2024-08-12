package com.example.pawpal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pawpal.screens.FirstScreen
import com.example.pawpal.screens.SecondScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route + "/{text}") {
            navArgument(name = "text") {
                StringType
            }
            SecondScreen(navController, it.arguments?.getString("text"))
        }
    }
}