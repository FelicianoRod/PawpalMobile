package com.example.weight.domain.model.walk

import com.example.weight.domain.model.alimentation.Alimentation
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val id: Int,
    val name: String,
    val walks: List<Walk>
)
