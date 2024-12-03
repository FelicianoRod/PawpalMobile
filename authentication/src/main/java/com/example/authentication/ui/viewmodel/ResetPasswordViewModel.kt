package com.example.authentication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.ui.model.PasswordState
import com.example.core.data.supabase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor() : ViewModel() {

    private val _passwordState = MutableStateFlow(PasswordState())
    val passwordState: StateFlow<PasswordState> = _passwordState.asStateFlow()

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success.asStateFlow()

    fun onPasswordChanged(password: String) {
        _passwordState.value = _passwordState.value.copy(password = password)
    }

    fun onPasswordRepeatChanged(passwordRepeat: String) {
        _passwordState.value = _passwordState.value.copy(passwordRepeat = passwordRepeat)
    }

    fun validatePasswords() {
        val error = validatePasswordState(
            passwordState.value.password,
            passwordState.value.passwordRepeat
        )

        _passwordState.value = _passwordState.value.copy(
            passwordErrors = error.first,
            passwordRepeatErrors = error.second
        )
    }

    private fun validatePasswordState(password: String, passwordRepeat: String): Pair<List<String>, List<String>> {
        val passwordErrors = mutableListOf<String>()
        val passwordRepeatErrors = mutableListOf<String>()

        if (password.length < 8) {
            passwordErrors.add("La contraseña debe tener al menos 8 caracteres.")
        }

        if (!password.any { it.isDigit() }) {
            passwordErrors.add("La contraseña debe contener al menos un número.")
        }

        if (!password.any { it.isUpperCase() }) {
            passwordErrors.add("La contraseña debe contener al menos una letra mayúscula.")
        }

        if (passwordRepeat != password) {
            passwordRepeatErrors.add("Las contraseñas no coinciden.")
        }

        return passwordErrors to passwordRepeatErrors
    }

    fun resetPassword() {
        if (_passwordState.value.passwordErrors.isNotEmpty() || _passwordState.value.passwordRepeatErrors.isNotEmpty()) {
            viewModelScope.launch {
                supabase.auth.updateUser {
                    password = _passwordState.value.password
                }
                _success.value = true
            }
        }
        _success.value = false
    }
}