package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DogWeight(
    val id : Int,
    val name : String,
    val weight_history : List<Weight>
)
