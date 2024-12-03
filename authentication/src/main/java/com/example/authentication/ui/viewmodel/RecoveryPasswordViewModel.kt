package com.example.authentication.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.ui.model.EmailState
import com.example.core.data.supabase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor() : ViewModel() {

    private val _emailState = MutableStateFlow(EmailState())
    val emailState: StateFlow<EmailState> = _emailState.asStateFlow()

    fun onEmailChanged(email: String) {
        _emailState.value = _emailState.value.copy(email = email)
    }

    fun isValidEmail(email: String) {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _emailState.value = _emailState.value.copy(
            isError = !isValid,
            errorList = if (isValid) emptyList() else listOf("El email no es válido")
        )
    }

    fun passwordResetRequest(email: String) {
        viewModelScope.launch {
            supabase.auth.resetPasswordForEmail(email)
        }
    }
}