package com.example.dogprofile.ui.models

import com.example.dogprofile.domain.Breed

data class DogFormStateModel (
    val name: String = "",
    val isOwner: Boolean = true,
    val birthdate: String = "",
    val gender: Boolean = true,
    val breedId: Int = 0,
    val description: String = "",
    val weight: Double = 0.0,
    val tags: List<String> = emptyList(),
    val imageUrl: String = "",

    val breeds: List<Breed> = emptyList()
)