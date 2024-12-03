package com.example.authentication.ui.model

data class EmailState (
    val email: String = "",
    val isError: Boolean = false,
    val errorList: List<String> = emptyList()
)