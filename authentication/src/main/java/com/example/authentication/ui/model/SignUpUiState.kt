package com.example.authentication.ui.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class SignUpUiState(

    // Email
    val email: String = "",
    val emailErrorList: SnapshotStateList<String> = mutableStateListOf(),
    val isEmailError: Boolean = false,

    // Password
    val password: String = "",
    val passwordErrorList: SnapshotStateList<String> = mutableStateListOf(),
    val isPasswordError: Boolean = false,

    // Confirm password
    val confirmPassword: String = "",
    val confirmPasswordErrorList: SnapshotStateList<String> = mutableStateListOf(),
    val isConfirmPasswordError: Boolean = false,

    // Form validation
    val validForm: Boolean = false
)