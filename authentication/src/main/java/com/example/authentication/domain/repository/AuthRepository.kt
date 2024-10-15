package com.example.authentication.domain.repository

interface AuthRepository {
    suspend fun signOut()
}