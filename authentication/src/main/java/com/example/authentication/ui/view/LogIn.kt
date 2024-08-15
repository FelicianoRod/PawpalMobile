package com.example.authentication.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.authentication.R
import com.example.authentication.ui.viewmodel.LoginViewModel
//import com.cursokotlin.mvvmlogin.R
import kotlinx.coroutines.launch

import androidx.compose.material3.ButtonColors


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(viewModel =  LoginViewModel(), navController = rememberNavController())
}

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel, navController)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))
//            LoginButton(true, loginEnable, navController)
            LoginButton(true, navController)
//            {
//                coroutineScope.launch {
//                    viewModel.onLoginSelected()
//                }
//            }
        }
    }
}

//fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit, navController: NavController) {
@Composable
fun LoginButton(loginEnable: Boolean, navController: NavController) {
    Button(
        onClick = {
//            onLoginSelected();
                    navController.navigate("home");

                  },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonColors(
            containerColor = Color(0xFFC8E0B4),
            contentColor = Color.Black,
            disabledContainerColor = Color(0xFFF78058),
            disabledContentColor = Color.White
        ),
        enabled = true
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color(0xFF636262),
            containerColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(

            cursorColor = Color(0xFF636262),
            containerColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
//    Image(
//        painter = painterResource(id = R.drawable.aris),
//        contentDescription = "Header",
//        modifier = modifier
//    )
//    import androidx.compose.ui.text.buildAnnotatedString
//    Text(
//        text = buildAnnotatedString {
//            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
//                append("Paw")
//            }
//            append("Pal")
//        },
//        fontSize = 24.sp
//    )
    Text(
        text = "PawPal",
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFC8E0B4)
    )
}