package com.example.userprofile.ui.view

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.components.TopAppBarSecondary
import com.example.core.ui.theme.PawpalTheme
import com.example.userprofile.ui.viewmodel.ProfileStateViewModel
import com.example.userprofile.ui.viewmodel.ThemeMode
import com.example.userprofile.ui.viewmodel.ThemeStateViewModel

@Preview(showBackground = true)
@Composable
fun AppearanceScreenPreview() {
    AppearanceScreen(navController = rememberNavController(), viewModel = ThemeStateViewModel())
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun AppearanceScreen(navController: NavController, viewModel: ThemeStateViewModel) {

    val themeMode = viewModel.themeMode.observeAsState(ThemeMode.System)

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
                Spacer(modifier = Modifier.height(16.dp))

                // Radios para seleccionar el tema
                RadioButtonGroup(
                    options = listOf(
                        "Dinámico",
                        "Sistema",
                        "Oscuro",
                        "Claro",
                    ),

                    selectedOption = when (themeMode.value) {
                        ThemeMode.Dynamic -> "Dinámico"
                        ThemeMode.System -> "Sistema"
                        ThemeMode.Dark -> "Oscuro"
                        else -> "Claro"
                    },
                    onOptionSelected = { option ->
                        when (option) {
                            "Dinámico" -> viewModel.setThemeMode(ThemeMode.Dynamic)
                            "Sistema" -> viewModel.setThemeMode(ThemeMode.System)
                            "Oscuro" -> viewModel.setThemeMode(ThemeMode.Dark)
                            "Claro" -> viewModel.setThemeMode(ThemeMode.Light)
                        }
                    }
                )

            }
        }

//    }
}

@Composable
fun RadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) }
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
