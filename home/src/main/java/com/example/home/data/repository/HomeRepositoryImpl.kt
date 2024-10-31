package com.example.home.data.repository

import android.util.Log
import com.example.core.data.supabase
import com.example.home.domain.model.Dog
import com.example.home.domain.repository.HomeRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    override suspend fun getListDogs(): Flow<List<Dog>> = flow {

        val session = supabase.auth.currentSessionOrNull()

        try {
            val columns = Columns.raw("""
                id,
                name,
                image_url
            """.trimIndent())

            val dogs = supabase.from("pets")
                .select(columns = columns) {
                    filter {
                        eq("profile_id", session?.user?.id ?: "")
                    }
                }.decodeList<Dog>()

            dogs.forEach { dog ->
                val imageUrl = dog.image_url?.let {
                    supabase.storage.from("image_upload").publicUrl(it)
                }
                dog.image_url = imageUrl
            }

            emit(dogs)
        } catch (e: Exception) {
            Log.d("DogRepositoryImpl", "getDogsUser: $e")
            null
        }
    }
}