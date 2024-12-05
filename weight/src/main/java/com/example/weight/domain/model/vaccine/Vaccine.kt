package com.example.weight.domain.model.vaccine

import kotlinx.serialization.Serializable

@Serializable
data class Vaccine(
    val id: Int,
    val name: String,
    val date: String,
    val next_due: String
)
