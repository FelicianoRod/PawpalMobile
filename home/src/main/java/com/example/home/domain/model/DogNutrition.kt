package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DogNutrition(
    val id: Int,
    val name: String,
    val pet_nutrition: List<Nutrition>
)
