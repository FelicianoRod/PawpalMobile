package com.example.authentication.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.authentication.ui.model.EmailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow<EmailModel>(EmailModel("", false, emptyList()))
    val email: StateFlow<EmailModel> = _email.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.value = _email.value.apply {
            this.email = email
        }
    }

    fun isValidEmail(email: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _email.value = _email.value.apply {
                isError = true
                errorList = listOf("El email no es válido")
            }
        } else {
            _email.value = _email.value.apply {
                isError = false
                errorList = emptyList()
            }
        }
    }
}