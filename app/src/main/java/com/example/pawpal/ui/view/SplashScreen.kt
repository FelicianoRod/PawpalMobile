package com.example.pawpal.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pawpal.navigation.AppScreens
import com.example.pawpal.ui.components.Spline
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}

@Composable
fun SplashScreen(navController: NavController) {

    var next by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(3000)
        next = true
    }

    if (next) {
        navController.navigate(AppScreens.LoginScreen.route) {
//            popUpTo(AppScreens.SplashScreen.route) {
//                inclusive = true
//            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Spline(url = "https://my.spline.design/untitled-7b879b3e1fd8134bb77688395144531b/")
        }

    }

}