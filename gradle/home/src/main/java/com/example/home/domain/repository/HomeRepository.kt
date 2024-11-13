package com.example.home.domain.repository

import com.example.home.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getListDogs(): Flow<List<Dog>>
}