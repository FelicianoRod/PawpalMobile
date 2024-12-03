package com.example.authentication.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.authentication.ui.viewmodel.ResetPasswordViewModel

@Preview(showBackground = true)
@Composable
fun ResetPasswordPreview() {
    ResetPasswordScreen(navController = NavController(LocalContext.current))
}

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
) {

    val passwordState by resetPasswordViewModel.passwordState.collectAsState()
    val success by resetPasswordViewModel.success.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Restablecer contraseña",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ingresa tu nueva contraseña",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = passwordState.password,
            onValueChange = {
                resetPasswordViewModel.onPasswordChanged(it)
            },
            label = { Text("Contraseña") },
            placeholder = { Text("*************") },
            modifier = Modifier.fillMaxWidth(),
            isError = passwordState.passwordErrors.isNotEmpty(),
            supportingText = {
                Column {
                    passwordState.passwordErrors.forEach { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passwordState.passwordRepeat,
            onValueChange = {
                resetPasswordViewModel.onPasswordRepeatChanged(it)
            },
            label = { Text("Repetir contraseña") },
            placeholder = { Text("*************") },
            modifier = Modifier.fillMaxWidth(),
            isError = passwordState.passwordRepeatErrors.isNotEmpty(),
            supportingText = {
                Column {
                    passwordState.passwordRepeatErrors.forEach { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                resetPasswordViewModel.validatePasswords()
                if (success) navController.navigate("home")
//                if (passwordState.passwordErrors.isEmpty() && passwordState.passwordRepeatErrors.isEmpty()) {
//                    navController.navigate("home")
//                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restablecer")
        }

    }
}
