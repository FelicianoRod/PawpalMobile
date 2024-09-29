package com.example.dogprofile.domain

import kotlinx.serialization.Serializable

@Serializable
data class Breed (
    val id: Int,
    val name: String
)