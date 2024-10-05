package com.example.pawpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.theme.PawpalTheme
import com.example.pawpal.navigation.AppNavigation
import com.example.userprofile.ui.viewmodel.ThemeMode
import com.example.userprofile.ui.viewmodel.ThemeStateViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val themeStateViewModel: ThemeStateViewModel by viewModels()
        setContent {
//            val themeStateViewMode = ThemeStateViewModel()
            val themeModeState by themeStateViewModel.themeMode.observeAsState()
            PawpalTheme(
                darkTheme = themeModeState == ThemeMode.Dark,
//                darkTheme = true,
                dynamicColor = themeModeState == ThemeMode.Dynamic,
                followSystemTheme = themeModeState == ThemeMode.System,
            ) {
                AppNavigation(themeStateViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PawpalTheme {
        AppNavigation(ThemeStateViewModel())
    }
}