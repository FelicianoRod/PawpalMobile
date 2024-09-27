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
import com.example.dogprofile.ui.view.AddDogScreen
import com.example.dogprofile.ui.view.DogProfileScreen
import com.example.dogprofile.ui.viewmodel.AddDogState
import com.example.home.ui.view.HomeScreen
import com.example.pawpal.screens.FirstScreen
import com.example.pawpal.screens.SecondScreen
import com.example.pawpal.ui.view.SplashScreen
import com.example.userprofile.ui.view.ProfileScreen
import com.example.userprofile.ui.view.UserProfileScreen
import com.example.userprofile.ui.viewmodel.ProfileStateViewModel
import com.example.userprofile.ui.viewmodel.UserProfileStateViewModel

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
            val userProfileViewModel = UserProfileStateViewModel()
            userProfileViewModel.getUserProfileState()
            UserProfileScreen(navController, userProfileViewModel)
        }

        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(viewModel = ProfileStateViewModel(), navController)
        }

        // DogProfile
        composable(route = AppScreens.DogProfileScreen.route) {
            DogProfileScreen(navController)
        }

        composable(route = AppScreens.AddDogScreen.route) {
            val addDogState = AddDogState()
            AddDogScreen(navController = navController, viewModel = addDogState)
        }


    }
}