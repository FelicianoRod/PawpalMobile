package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class breeds (
    val id: Int,
    val name: String
)