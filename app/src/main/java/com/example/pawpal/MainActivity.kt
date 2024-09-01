package com.example.pawpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.pawpal.navigation.AppNavigation
import com.example.pawpal.screens.FirstScreen
import com.example.pawpal.ui.theme.PawpalTheme
import com.example.pawpal.ui.view.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PawpalTheme {
//                SplashScreen(navController = rememberNavController())
//                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
//                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    FirstScreen()
//                }
            }
        }
    }
} // Test

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PawpalTheme {
        AppNavigation()
    }
}