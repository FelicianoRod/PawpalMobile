package com.example.home.data.repository

import android.util.Log
import com.example.core.data.supabase
import com.example.home.domain.model.DogWalk
import com.example.home.domain.repository.WalkRepository
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WalkRepositoryImpl @Inject constructor() : WalkRepository {

    override suspend fun getWalks(id: Int) : Flow<DogWalk> = flow {
        try {
            val columns = Columns.raw("""
                id,
                name,
                walks (
                    id,
                    day,
                    distance
                )
            """.trimIndent())

            val dog = supabase.from("pets")
                .select(columns = columns) {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingle<DogWalk>()

            emit(dog)

        } catch (e: Exception) {
            Log.d("WalkRepository", "getWalks: $e")
            null
        }
    }
}