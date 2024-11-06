package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DogWalk(
    val id: Int,
    val name: String,
    val walks: List<Walk>
)
