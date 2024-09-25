package com.example.userprofile.ui.view

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.components.TopAppBarSecondary
import com.example.core.ui.theme.PawpalTheme
import com.example.userprofile.ui.viewmodel.ProfileStateViewModel

@Preview(showBackground = true)
@Composable
fun AppearanceScreenPreview() {
    AppearanceScreen(navController = rememberNavController())
}

@Composable
fun AppearanceScreen(navController: NavController) {

    PawpalTheme {

        Scaffold(
            topBar = { TopAppBarSecondary("Apariencia", navController)}
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
            ) {
                Text(
                    text = "Tema",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Text(
                    text = "Selecciona el tema para el panel.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

            }
        }

    }
}