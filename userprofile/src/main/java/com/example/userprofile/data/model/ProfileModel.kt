package com.example.userprofile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String?,
    val full_name: String? = "Jose",
    val email: String? = "algo",
    val avatar_url: String?,
    val biography: String?,
    val urls: String?,
    val cellphone: String?,
    val date_of_birth: String?,
    val city: String?,
    val range: Int?,
    val premium: String?
)