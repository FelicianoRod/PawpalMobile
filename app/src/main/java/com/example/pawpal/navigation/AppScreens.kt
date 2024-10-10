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

//    User Profile
    object ProfileScreen: AppScreens("profile_screen")
    object AppearanceScreen: AppScreens("appearance_screen")
    object NotificationSettingsScreen: AppScreens("notification_settings_screen")
//    object VisualSettingsScreen: AppScreens("visual_settings_screen")

//  Dog profile
    object DogProfileScreen: AppScreens("dog_profile")
    object AddDogScreen: AppScreens("add_dog")
    object DogInformationScreen: AppScreens("dog_information/{dogId}") {
        fun createRoute(dogId: Int) = "dog_information/$dogId"

    }

}