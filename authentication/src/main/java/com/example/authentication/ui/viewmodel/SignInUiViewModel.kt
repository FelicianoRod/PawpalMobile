package com.example.authentication.ui.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.authentication.domain.viewmodel.signInWithEmailDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

//    State
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _buttonEnabled = MutableStateFlow(false)
    val buttonEnabled: StateFlow<Boolean> = _buttonEnabled

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _buttonEnabled.value = isValidEmail(email) && isValidPassword(password)

    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean  = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun signInButton(navController: NavController) {
        signInWithEmailUi(email = email.value, password = password.value, navController = navController)
    }

    private fun signInWithEmailUi(email: String, password: String, navController: NavController) {
        try {
            viewModelScope.launch {
                signInWithEmailDomain(email = email, password = password, navController = navController)
            }
        } catch (e: Exception) {
            Log.e("SignInWithEmailUi", "Error during sign in - view", e)
        }
    }

//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _password = MutableLiveData<String>()
//    val password: LiveData<String> = _password
//
//    private val _loginEnable = MutableLiveData<Boolean>()
//    val loginEnable: LiveData<Boolean> = _loginEnable
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun onLoginChanged(email: String, password: String) {
//        _email.value = email
//        _password.value = password
//        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
//    }
//
//    private fun isValidPassword(password: String): Boolean = password.length > 6
//
//    private fun isValidEmail(email: String): Boolean  = Patterns.EMAIL_ADDRESS.matcher(email).matches()
//
//    suspend fun onLoginSelected() {
//        _isLoading.value = true
//        delay(4000)
//        _isLoading.value = false
//    }

}