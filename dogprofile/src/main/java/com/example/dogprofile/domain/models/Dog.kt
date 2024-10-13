package com.example.dogprofile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Dog (
    val id: Int,
    var image_url: String?,
    val name: String,
    val breeds: breeds,
    val tags: List<Tag>?
)