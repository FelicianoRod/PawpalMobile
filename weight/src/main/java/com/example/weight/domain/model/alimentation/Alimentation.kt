package com.example.weight.domain.model.alimentation

import kotlinx.serialization.Serializable

@Serializable
data class Alimentation(
    val id: Int,
    val food_amount: Double,
    val food_type: String,
    val created_at: String
)
