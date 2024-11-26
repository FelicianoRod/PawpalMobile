package com.example.weight.domain.repository

import com.example.weight.domain.model.Weight
import kotlinx.coroutines.flow.Flow

interface WeightRepositoryy {
    suspend fun getWeight(id: Int, startDate: String, endDate: String) : Flow<List<Weight>>
}