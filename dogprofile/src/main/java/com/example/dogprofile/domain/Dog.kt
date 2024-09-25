package com.example.dogprofile.domain

import kotlinx.serialization.Serializable

@Serializable
data class Dog (
    val id: Int,
    val profile_id: String,
    val breed_id: Int,
    val name: String,
    val birthdate: String,
    val gender: String,
    val create_at: String,
    val image_url: String,
    val tags: String,
    val owner_name: String,
    val description: String,
    val health: String,
    val weight: Double
)