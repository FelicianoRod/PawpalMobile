package com.example.weight.domain.model.vaccine

import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val id: Int,
    val name: String,
    val vaccinations: List<Vaccine>
)
