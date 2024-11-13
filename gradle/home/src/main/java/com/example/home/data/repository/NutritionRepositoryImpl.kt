package com.example.home.data.repository

import android.util.Log
import com.example.core.data.supabase
import com.example.home.domain.model.DogNutrition
import com.example.home.domain.repository.NutritionRepository
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NutritionRepositoryImpl @Inject constructor() : NutritionRepository {

    override suspend fun getNutrition(id: Int) : Flow<DogNutrition> = flow {

        try {
            val columns = Columns.raw("""
                id,
                name,
                pet_nutrition (
                    id,
                    food_amount,
                    created_at
                )
            """.trimIndent())

            val dog = supabase.from("pets")
                .select(columns = columns) {
                    filter {
                        eq("id", id)
                        }
                }
                .decodeSingle<DogNutrition>()

            emit(dog)

        } catch (e: Exception) {
            Log.d("NutritionRepository", "getNutrition: $e")
            null
        }
    }
}