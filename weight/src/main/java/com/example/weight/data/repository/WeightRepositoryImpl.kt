package com.example.weight.data.repository

import android.util.Log
import com.example.core.data.supabase
import com.example.weight.domain.model.DogWeight
import com.example.weight.domain.model.Weight
import com.example.weight.domain.repository.WeightRepositoryy
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeightRepositoryImpll @Inject constructor() : WeightRepositoryy {

    override suspend fun getWeight(id: Int, startDate: String, endDate: String) : Flow<List<Weight>> = flow {
        Log.d("WeightRepositoryImpll", "getWeight: $startDate, $endDate")
        try {
            val columns = Columns.raw("""
                id,
                name,
                weight_history!inner (
                    id,
                    created_at,
                    pet_id,
                    weight
                )
            """.trimIndent())

            val dog = supabase.from("pets")
                .select(columns = columns) {
                    filter {
                        and(
                            referencedTable = "weight_history",
                        ) {
                            gte("created_at", startDate)
                            lte("created_at", endDate)
                        }
                        eq("id", id)
                    }
                }
                .decodeSingle<DogWeight>()

            emit(dog.weight_history)

        } catch (e: Exception) {
            null
        }
// test
    }
}