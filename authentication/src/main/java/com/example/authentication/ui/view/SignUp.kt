package com.example.authentication.ui.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.authentication.ui.model.SignUpUiState
import com.example.authentication.ui.partials.ButtonCustom
import com.example.authentication.ui.partials.TextFieldCustom
import com.example.authentication.ui.viewmodel.SignUpViewModel
import io.github.jan.supabase.SupabaseClient

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(viewModel = SignUpViewModel())
}

@Composable
fun SignUpScreen(viewModel: SignUpViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SignUp(viewModel)
    }
}

//@SuppressLint("SuspiciousIndentation")
@Composable
fun SignUp(viewModel: SignUpViewModel) {

//    val email: String by viewModel.email.observeAsState(initial = "")
//    val password: String by viewModel.password.observeAsState(initial = "")
//    val confirmPassword: String by viewModel.confirmPassword.observeAsState(initial = "")
//    val signupEnable: Boolean by viewModel.signupEnable.observeAsState(initial = false)
//    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
//    val coroutineScope = rememberCoroutineScope()

    val signUpUiState = viewModel.signUpUiState.observeAsState(SignUpUiState()).value

//    if (isLoading) {
//        Box(Modifier.fillMaxSize()) {
//            CircularProgressIndicator(Modifier.align(Alignment.Center))
//        }
//    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro",
                modifier = Modifier
//                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(16.dp),

//                    .we(FontWeight.Bold)
//                    .fontSize(24.sp)
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            val iframe = "https://my.spline.design/untitled-7b879b3e1fd8134bb77688395144531b/"
            Show3DDesign(url = iframe)
//            WebView()
//            Spacer(modifier = Modifier.padding(16.dp))
//            EmailFieldSU(email) { viewModel.onSignUpChanged(it, password, confirmPassword) }
//            Spacer(modifier = Modifier.padding(4.dp))
//            PasswordFieldSU(password) { viewModel.onSignUpChanged(email, it, confirmPassword)}
//            Spacer(modifier = Modifier.padding(8.dp))
//            ConfirmPasswordFieldSU(confirmPassword) { viewModel.onSignUpChanged(email, password, it) }
//            Spacer(modifier = Modifier.padding(16.dp))
//            SignUpButton(false)
            EmailSignUpField(
                value = signUpUiState.email,
                onValueChanged = { viewModel.onEmailChange(it);
                    viewModel.validateEmail(it) },
                isError = signUpUiState.isEmailError,
                supportingText = signUpUiState.emailErrorList
            )
            PasswordFieldSU(
                value = signUpUiState.password,
                onValueChanged = { viewModel.onPasswordChange(it);
                                    viewModel.validatePassword(it, viewModel.signUpUiState.value?.confirmPassword ?: "") },
                isError = signUpUiState.isPasswordError,
                supportingText = signUpUiState.passwordErrorList
            )
            ConfirmPasswordSignUpField(
                value = signUpUiState.confirmPassword,
                onValueChanged = { viewModel.onConfirmPasswordChange(it);
                                    viewModel.validateConfirmPassword(it) },
                isError = signUpUiState.isConfirmPasswordError,
                supportingText = signUpUiState.confirmPasswordErrorList
            )
            SignUpButton(signUpUiState.validForm)
        }
}

@Composable
fun EmailSignUpField(
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean,
//    supportingText: String
    supportingText: List<String>
) {
    TextFieldCustom(
        value = value,
        onValueChanged = onValueChanged,
        label = "Email",
        isError = isError,
        supportingText = supportingText
    )
}

@Composable
fun PasswordFieldSU(
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean,
    supportingText: List<String>
) {
    TextFieldCustom(
        value = value,
        onValueChanged = onValueChanged,
        label = "Password",
        isError = isError,
        supportingText = supportingText
    )
}

@Composable
fun ConfirmPasswordSignUpField(
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean,
//    supportingText: String
    supportingText: List<String>
) {
    TextFieldCustom(
        value = value,
        onValueChanged = onValueChanged,
        label = "Repetir contraseña",
        isError = isError,
        supportingText = supportingText
    )
}

@Composable
fun SignUpButton(enabled: Boolean) {
    ButtonCustom(
        label = "Registrar",
        enabled = enabled,
//        onSignUpSelected = onSignUpSelected
    )
}

@Composable
fun SplineWebView(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}

//@SuppressLint("SetJavaScriptEnabled")
//@Composable
//fun SplineView(iframeCode: String) {
//    AndroidView(
//        factory = { context ->
//            WebView(context).apply {
////                settings.javaScriptEnabled = true
//                loadData(iframeCode, "text/html", "utf-8")
//            }
//        },
//        update = { webView ->
//            webView.loadData(iframeCode, "text/html", "utf-8")
//        }
//    )
//}

@Composable
fun SplineView(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, modifier = Modifier.fillMaxSize())
}

@Composable
fun WebView(){

    // Declare a string that contains a url
    val mUrl = "https://my.spline.design/untitled-7b879b3e1fd8134bb77688395144531b/"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

@Composable
fun WebViewScreen() {

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            webView.loadUrl("https://www.ldoceonline.com/")
        }
    )
}

@Composable
fun Show3DDesign(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                domStorageEnabled = true
                builtInZoomControls = true
                displayZoomControls = false
                allowFileAccess = true
                allowContentAccess = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                cacheMode = WebSettings.LOAD_DEFAULT
//                setAppCacheEnabled(true)
//                setAppCachePath(context.cacheDir.path)
            }
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.loadUrl("javascript:(function() { " +
                            "var meta = document.createElement('meta'); " +
                            "meta.name = 'viewport'; " +
                            "meta.content = 'width=device-width, initial-scale=1.0'; " +
                            "document.getElementsByTagName('head')[0].appendChild(meta);" +
                            "})()")
                }
            }
            webChromeClient = WebChromeClient()  // Añade esta línea para manejar mejor el contenido interactivo
            loadUrl(url)
        }
    }, modifier = Modifier.fillMaxSize())
}