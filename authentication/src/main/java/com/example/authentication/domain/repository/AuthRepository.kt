package com.example.authentication.domain.repository

import com.example.authentication.data.models.User

interface AuthRepository {
    suspend fun signOut()
    suspend fun signUp(user: User)
}