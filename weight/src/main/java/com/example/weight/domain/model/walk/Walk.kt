package com.example.weight.domain.model.walk

import kotlinx.serialization.Serializable

@Serializable
data class Walk(
    val id: Int,
    val day: String,
    val distance: String,
    val total_time: String,
    val notes: String
)
