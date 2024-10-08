package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DogOwner (
    val id: String,
    val full_name: String,
    val city: String
)