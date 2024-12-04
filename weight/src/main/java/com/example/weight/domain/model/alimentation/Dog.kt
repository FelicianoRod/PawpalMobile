package com.example.weight.domain.model.alimentation

import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val id : Int,
    val name : String,
    val pet_nutrition : List<Alimentation>
)