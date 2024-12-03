package com.example.authentication.ui.model

data class PasswordState(
    val password: String = "",
    val passwordRepeat: String = "",
    val passwordErrors: List<String> = emptyList(),
    val passwordRepeatErrors: List<String> = emptyList()
)

