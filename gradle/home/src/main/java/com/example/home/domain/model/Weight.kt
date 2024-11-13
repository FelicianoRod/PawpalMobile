package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Weight (
    val id : Int,
    val created_at : String,
    val pet_id: Int,
    val weight : Double
)