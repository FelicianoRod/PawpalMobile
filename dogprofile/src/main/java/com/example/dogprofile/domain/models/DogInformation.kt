package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DogInformation (
    var image_url: String?,
    val name: String,
    val breeds: breeds,
    val birthdate: String?,
    val profiles: profiles,
    val description: String?
)