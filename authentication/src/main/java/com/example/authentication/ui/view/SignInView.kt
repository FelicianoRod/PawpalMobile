package com.example.authentication.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.authentication.ui.viewmodel.LoginViewModel
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.authentication.R
import com.example.core.ui.components.TextFieldForm

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(viewModel = LoginViewModel(), navController = rememberNavController())
}

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val buttonEnabled by viewModel.buttonEnabled.collectAsState()
    val message by viewModel.message.collectAsState()

    Scaffold() { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (isLoading) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    EmailField(email) { viewModel.onLoginChanged(it, password) }
                    PasswordField(password) { viewModel.onLoginChanged(email, it) }
                    Spacer(modifier = Modifier.padding(8.dp))
//                    ForgotPassword(Modifier.align(Alignment.End))
                    Spacer(modifier = Modifier.padding(16.dp))
                    LoginButton(viewModel, buttonEnabled, navController)
                    Spacer(modifier = Modifier.height(16.dp))
//                    SignUpButton(navController)
                }
            }
        }
    }
}

@Composable
fun LoginButton(viewModel: LoginViewModel, loginEnable: Boolean, navController: NavController) {
    Button(
        onClick = {
            viewModel.signInButton(navController)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Black,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = Color.White
        ),
        enabled = loginEnable
    ) {
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun SignUpButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("sign_up");
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = Color.Black,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = Color.White
        ),
    ) {
        Text(
            text = "Registrarte",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        style = MaterialTheme.typography.bodySmall,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {

    var passwordVisible by remember { mutableStateOf(false) }
    var visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

    TextFieldForm(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        label = "Contraseña",
        placeholder = "Contraseña",
        keyboardType = KeyboardType.Password,
        visualTransformation = visualTransformation,
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Default.Visibility
            else Icons.Default.VisibilityOff

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null)
            }
        }
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextFieldForm(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        label = "Correo electrónico",
        placeholder = "fulanito@gmail.com",
        keyboardType = KeyboardType.Email,
    )
}