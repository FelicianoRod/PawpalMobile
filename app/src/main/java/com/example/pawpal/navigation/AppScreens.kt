package com.example.pawpal.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object SplashScreen: AppScreens("splash_screen")
//    Auth
    object LoginScreen: AppScreens("login")
    object SignUpScreen: AppScreens("sign_up")
    object HomeScreen: AppScreens("home")
    object UserProfileScreen: AppScreens("user_profile")
    object DogProfileScreen: AppScreens("dog_profile")

//    User Profile
    object ProfileScreen: AppScreens("profile_screen")


}