package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Tag (
    val id: String,
    val text: String
)