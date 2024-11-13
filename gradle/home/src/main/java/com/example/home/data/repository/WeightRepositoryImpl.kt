package com.example.home.data.repository

import android.util.Log
import com.example.core.data.supabase
import com.example.home.domain.model.DogWeight
import com.example.home.domain.model.Weight
import com.example.home.domain.repository.WeightRepository
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeightRepositoryImpl @Inject constructor() : WeightRepository {

    override suspend fun getWeight(id: Int) : Flow<List<Weight>> = flow {
        try {
            val columns = Columns.raw("""
                id,
                name,
                weight_history (
                    id,
                    created_at,
                    pet_id,
                    weight
                )
            """.trimIndent())

            val dog = supabase.from("pets")
                .select(columns = columns) {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingle<DogWeight>()

            emit(dog.weight_history)

        } catch (e: Exception) {
            null
        }

    }
}