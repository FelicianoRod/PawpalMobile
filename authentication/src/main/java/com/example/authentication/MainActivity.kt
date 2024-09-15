package com.example.authentication

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
//import com.example.authentication.screens.RegisterScreen
import com.example.authentication.ui.theme.PawpalTheme
import com.example.authentication.ui.view.SignUpScreen
import com.example.authentication.ui.viewmodel.SignUpViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PawpalTheme {
                SignUpScreen(viewModel = SignUpViewModel(), navController = NavController(LocalContext.current))
            }
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }
}





