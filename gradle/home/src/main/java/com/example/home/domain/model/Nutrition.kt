package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Nutrition(
    val id: Int,
    val created_at: String,
    val food_amount: Float,
)
