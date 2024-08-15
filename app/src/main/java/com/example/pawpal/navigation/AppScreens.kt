package com.example.pawpal.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login")
    object HomeScreen: AppScreens("home")
    object UserProfileScreen: AppScreens("user_profile")
    object DogProfileScreen: AppScreens("dog_profile")

}