package com.example.userprofile.ui.models

import androidx.compose.runtime.Composable

data class SettingsOptionUserProfile(
    val icon: Int,
    val text: String,
    val route: () -> Unit
)