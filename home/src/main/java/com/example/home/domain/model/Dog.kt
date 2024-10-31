package com.example.home.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Dog (
    val id: Int,
    val name: String,
    var image_url: String?
)