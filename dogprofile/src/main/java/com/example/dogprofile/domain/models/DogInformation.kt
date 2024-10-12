package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class DogInformation (
    var image_url: String? = "https://img.freepik.com/foto-gratis/adorable-perro-basenji-marron-blanco-sonriendo-dando-maximo-cinco-aislado-blanco_346278-1657.jpg",
    val name: String,
    val breeds: breeds,
    val birthdate: String? = "2023-01-01",
    val profiles: profiles,
    val description: String? = "algo"
)