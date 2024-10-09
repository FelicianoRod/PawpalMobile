package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class profiles (
    val id: String,
    val full_name: String? = "Pedro Perez",
    val city: String? = "Ocoyork"
)