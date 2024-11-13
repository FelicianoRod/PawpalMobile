package com.example.home.domain.repository

import com.example.home.domain.model.DogNutrition
import com.example.home.domain.model.Nutrition
import kotlinx.coroutines.flow.Flow

interface NutritionRepository {
    suspend fun getNutrition(id: Int) : Flow<DogNutrition>
}