package com.example.pawpal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.authentication.ui.view.LoginScreen
import com.example.authentication.ui.view.SignUpScreen
import com.example.authentication.ui.viewmodel.LoginViewModel
import com.example.authentication.ui.viewmodel.SignUpViewModel
import com.example.core.ui.theme.PawpalTheme
import com.example.dogprofile.data.supabase.DogRepositoryImpl
import com.example.dogprofile.domain.repository.DogInformationViewModelFactory
import com.example.dogprofile.domain.repository.DogRepository
import com.example.dogprofile.ui.view.AddDogScreen
import com.example.dogprofile.ui.view.DogInformationScreen
import com.example.dogprofile.ui.view.DogProfileScreen
import com.example.dogprofile.ui.viewmodel.AddDogState
import com.example.dogprofile.ui.viewmodel.DogInformationViewModel
import com.example.dogprofile.ui.viewmodel.DogStateViewModel
import com.example.home.ui.view.HomeScreen
import com.example.pawpal.screens.FirstScreen
import com.example.pawpal.screens.SecondScreen
import com.example.pawpal.ui.view.SplashScreen
import com.example.userprofile.ui.view.AppearanceScreen
import com.example.userprofile.ui.view.NotificationSettingsScreen
import com.example.userprofile.ui.view.ProfileScreen
import com.example.userprofile.ui.view.UserProfileScreen
import com.example.userprofile.ui.viewmodel.ProfileStateViewModel
import com.example.userprofile.ui.viewmodel.ThemeStateViewModel
import com.example.userprofile.ui.viewmodel.UserProfileStateViewModel

@Composable
fun AppNavigation(themeStateViewModel: ThemeStateViewModel) {

//    PawpalTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = AppScreens.SplashScreen.route
        ) {

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
//                val userProfileViewModel = UserProfileStateViewModel()
                val userProfileViewModel: UserProfileStateViewModel = viewModel()
                userProfileViewModel.getUserProfileState()
                UserProfileScreen(navController, userProfileViewModel)
            }

            composable(route = AppScreens.ProfileScreen.route) {
                ProfileScreen(viewModel = ProfileStateViewModel(), navController)
            }

            composable(route = AppScreens.AppearanceScreen.route) {
                AppearanceScreen(navController, themeStateViewModel)
            }

            composable(route = AppScreens.NotificationSettingsScreen.route) {
                NotificationSettingsScreen(navController)
            }


            // DogProfile
            composable(route = AppScreens.DogProfileScreen.route) {
//            val dogStateViewModel = DogStateViewModel()
//                dogStateViewModel.getDogsUserList()
//            DogProfileScreen(navController, dogStateViewModel)
                DogProfileScreen(navController)
            }

            composable(route = AppScreens.AddDogScreen.route) {
                val addDogState = AddDogState()
                addDogState.getBreedsList()
                AddDogScreen(navController = navController, viewModel = addDogState)
            }

            composable(route = AppScreens.DogInformationScreen.route) {

                val dogInformationViewModel: DogInformationViewModel = viewModel(
                    factory = DogInformationViewModelFactory(DogRepositoryImpl())
                )

                dogInformationViewModel.getDogInformation()
                DogInformationScreen(navController, dogInformationViewModel)
            }
        }
//    }
}