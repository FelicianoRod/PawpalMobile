package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DogInformation (
    val image_url: String,
    val name: String,
    val breeds: BreedDog,
    val birthdate: String,
    val profiles: DogOwner
)