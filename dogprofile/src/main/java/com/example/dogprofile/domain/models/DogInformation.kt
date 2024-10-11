package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DogInformation (
    var image_url: String? = "",
    val name: String,
    val breeds: breeds,
    val birthdate: String? = "2023-01-01",
    val profiles: profiles,
    val description: String? = "algo"
)