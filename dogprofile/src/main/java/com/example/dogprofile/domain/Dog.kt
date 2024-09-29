package com.example.dogprofile.domain

import kotlinx.serialization.Serializable

@Serializable
data class Dog (
    val id: Int? = null,
    val profile_id: String,
    val breed_id: Int,
    val name: String,
    val birthdate: String? = null,
    val gender: String,
    val create_at: String? = null,
    val image_url: String? = null,
    val tags: String? = null,
    val owner_name: String? = null,
    val description: String,
    val health: String? = null,
    val weight: Double
)