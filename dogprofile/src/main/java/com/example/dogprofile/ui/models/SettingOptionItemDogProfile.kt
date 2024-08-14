package com.example.dogprofile.ui.models

import androidx.compose.runtime.Composable

data class SettingOption(
    val icon: @Composable () -> Unit,
    val text: String,
    val onClick: () -> Unit
)