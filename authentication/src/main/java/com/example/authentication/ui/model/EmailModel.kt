package com.example.authentication.ui.model

data class EmailModel (
    var email: String,
    var isError: Boolean,
    var errorList: List<String>
)