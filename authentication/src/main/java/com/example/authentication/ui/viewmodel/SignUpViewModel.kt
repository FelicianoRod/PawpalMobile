package com.example.authentication.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authentication.ui.model.SignUpUiState

class SignUpViewModel: ViewModel() {
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _password = MutableLiveData<String>()
//    val password: LiveData<String> = _password
//
//    private val _confirmPassword = MutableLiveData<String>()
//    val confirmPassword: LiveData<String> = _confirmPassword
//
//
//    private val _signupEnable = MutableLiveData<Boolean>()
//    val signupEnable: LiveData<Boolean> = _signupEnable
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _signUpUiState = MutableLiveData<SignUpUiState>()
//    val signUpUiState: LiveData<SignUpUiState> = _signUpUiState
////    private val _
//
//    fun onSignUpChanged(
//        email: String,
//        password: String,
//        confirmPassword: String
//    ) {
//        _email.value = email
//        _password.value = password
//        validatePassword(password)
//        _confirmPassword.value = confirmPassword
//        _signupEnable.value = if (password == confirmPassword && isValidPassword(password) && isValidEmail(email)) true else false
//    }
//
//    private fun isValidPassword(password: String): Boolean = password.length > 7
//
//    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
//
////    otro
//    var suui by mutableStateOf(SignUpUiState())
//    private set
//
//    fun onPasswordChange(password: String) {
//        suui = suui.copy(password = password)
//    }
//
////    Errors
//    fun validatePassword(password: String) {
////        val password = suui.password
//        if (password.length < 8) {
//            _signUpUiState.value = SignUpUiState(passwordError = "La contraseña debe tener al menos 8 caracteres.")
////            suui = suui.copy(passwordError = "La contraseña debe tener al menos 8 caracteres.")
//        } else {
//            suui = suui.copy(passwordError = null)
//        }
//    }

    // Estado
    private val _signUpUiState = MutableLiveData(SignUpUiState())
    val signUpUiState: LiveData<SignUpUiState> = _signUpUiState

    // Eventos
    fun onEmailChange(email: String) {
        val currentState = _signUpUiState.value ?: SignUpUiState()
        _signUpUiState.value = currentState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        val currentState = _signUpUiState.value ?: SignUpUiState()
        _signUpUiState.value = currentState.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        val currentState = _signUpUiState.value ?: SignUpUiState()
        _signUpUiState.value = currentState.copy(confirmPassword = confirmPassword)
    }

    // Validaciones
    fun validateEmail(email: String) {
        val currentState = _signUpUiState.value ?: SignUpUiState()
        val errors = mutableStateListOf<String>()
        val validForm: Boolean

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.add("El email no es válido")
        }

        if (errors.isEmpty()) {
            val correctPassword: Boolean = signUpUiState.value?.isPasswordError ?: false
            val correctConfirmPassword: Boolean = signUpUiState.value?.isConfirmPasswordError ?: false

            if (!correctConfirmPassword && !correctPassword) {
                validForm = true
            } else {
                validForm = false
            }

            _signUpUiState.value = currentState.copy(
                isEmailError = false,
                emailErrorList = errors,
                validForm = validForm
            )
        } else {
            _signUpUiState.value = currentState.copy(
                isEmailError = true,
                emailErrorList = errors
            )
        }
    }

    fun validatePassword(password: String, confirmPassword: String) {
        val currentState = _signUpUiState.value ?: SignUpUiState()
        val errors = mutableStateListOf<String>()
        val validForm: Boolean
        val updateErrorList = currentState.confirmPasswordErrorList.toMutableStateList()


        if (password.length < 8) {
            errors.add("La contraseña debe tener al menos 8 caracteres")
        }

        // Eliminar el mensaje de "El password no coincide"
        if (confirmPassword == password) {
            updateErrorList.remove("Las contraseñas no coinciden")
        }

        if (errors.isEmpty()) {
            val correctEmail: Boolean = signUpUiState.value?.isEmailError ?: false
            val correctConfirmPassword: Boolean = signUpUiState.value?.isConfirmPasswordError ?: false

            if (!correctEmail && !correctConfirmPassword) {
                validForm = true
            } else {
                validForm = false
            }

            _signUpUiState.value = currentState.copy(
                isPasswordError = false,
                passwordErrorList = errors,
                validForm = validForm,
                confirmPasswordErrorList = updateErrorList
            )
        } else {
            _signUpUiState.value = currentState.copy(
                isPasswordError = true,
                passwordErrorList = errors,
                confirmPasswordErrorList = updateErrorList
            )
        }
    }

    fun validateConfirmPassword(confirmPassword: String) {
        val currentState = _signUpUiState.value ?: SignUpUiState()
        val errors = mutableStateListOf<String>()
        val validForm: Boolean

        if (confirmPassword.length < 8) {
            errors.add("La contraseña debe tener al menos 8 caracteres")
        }
        if (confirmPassword != currentState.password) {
            errors.add("Las contraseñas no coinciden")
        }

        if (errors.isEmpty()) {
            val correctEmail: Boolean = signUpUiState.value?.isEmailError ?: false
            val correctPassword: Boolean = signUpUiState.value?.isPasswordError ?: false

            if (!correctEmail && !correctPassword) {
                validForm = true
            } else {
                validForm = false
            }

            _signUpUiState.value = currentState.copy(
                isConfirmPasswordError = false,
                confirmPasswordErrorList = errors,
                validForm = validForm
            )

        } else {
            _signUpUiState.value = currentState.copy(
                isConfirmPasswordError = true,
                confirmPasswordErrorList = errors
            )
        }
    }
}