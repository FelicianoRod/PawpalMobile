package com.example.pawpal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.authentication.ui.view.LoginScreen
import com.example.authentication.ui.view.SignUpScreen
import com.example.authentication.ui.viewmodel.LoginViewModel
import com.example.authentication.ui.viewmodel.SignUpViewModel
import com.example.dogprofile.ui.view.DogProfileScreen
import com.example.home.ui.view.HomeScreen
import com.example.pawpal.screens.FirstScreen
import com.example.pawpal.screens.SecondScreen
import com.example.pawpal.ui.view.SplashScreen
import com.example.userprofile.ui.view.UserProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        // Primera pantalla
        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)
        }
        // Segunda pantalla
        composable(route = AppScreens.SecondScreen.route + "/{text}") {
            navArgument(name = "text") {
                StringType
            }
            SecondScreen(navController, it.arguments?.getString("text"))
        }
        // SplashScreen
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }

        // Auth
        composable(route = AppScreens.LoginScreen.route) {
            val loginViewModel: LoginViewModel = LoginViewModel()
            LoginScreen(loginViewModel, navController)
        }
        composable(route = AppScreens.SignUpScreen.route) {
            val signUpViewModel: SignUpViewModel = SignUpViewModel()
            SignUpScreen(signUpViewModel, navController)
        }

        // Home
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        // UserProfile
        composable(route = AppScreens.UserProfileScreen.route) {
            UserProfileScreen(navController)
        }
        // DogProfile
        composable(route = AppScreens.DogProfileScreen.route) {
            DogProfileScreen(navController)
        }


    }
}