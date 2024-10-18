package com.example.pawpal.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.authentication.R
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
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Row(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.footprint),
                    contentDescription = "Logo de Pawpal",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "PawPal",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
//            Spline(url = "https://my.spline.design/untitled-7b879b3e1fd8134bb77688395144531b/")
            }
        }
    }
}