package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Walk(
    val id: Int,
    val day: String,
    val distance: String,
)
