package com.example.dogprofile.ui.models

data class DogFormStateModel (
    val name: String = "",
    val isOwner: Boolean = false,
    val birthdate: String = "",
    val gender: String = "",
    val breedId: Int = 0,
    val description: String = "",
    val weight: Double = 0.0,
    val tags: List<String> = emptyList(),
    val imageUrl: String = ""
)