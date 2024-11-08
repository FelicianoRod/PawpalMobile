package com.example.home.domain.repository

import com.example.home.domain.model.Weight
import kotlinx.coroutines.flow.Flow

interface WeightRepository {
    suspend fun getWeight(id: Int) : Flow<List<Weight>>
}