package com.example.userprofile.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.ui.components.TopAppBarSecondary
import com.example.core.ui.theme.PawpalTheme

@Composable
fun NotificationSettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBarSecondary("Ajustes de notificaciones", navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(
                text = "Notificaciones",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Text(
                text = "Configura cómo recibes las notificaciones de Pawpal.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

        }
    }
}