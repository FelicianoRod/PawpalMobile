package com.example.authentication.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authentication.ui.viewmodel.RecoveryPasswordViewModel

@Preview(showBackground = true)
@Composable
fun RecoveryPasswordPreview() {
    RecoveryPasswordScreen()
}

@Composable
fun RecoveryPasswordScreen(
    recoveryPasswordViewModel: RecoveryPasswordViewModel = hiltViewModel()
) {
    val email by recoveryPasswordViewModel.email.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar contraseña",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ingresa tu email para recuperar tu contraseña",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = email.email,
            onValueChange = {
                recoveryPasswordViewModel.onEmailChanged(it)
                recoveryPasswordViewModel.isValidEmail(it)
            },
            label = { Text("Email") },
            placeholder = { Text("email@merchypart.hk") },
            modifier = Modifier.fillMaxWidth(),
            isError = email.isError,
            supportingText = {
                if (email.isError) {
                    Text(
                        text = email.errorList.joinToString(),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar contraseña")
        }
    }
}
