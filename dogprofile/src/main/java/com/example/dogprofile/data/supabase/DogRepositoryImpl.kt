package com.example.dogprofile.data.supabase

import android.util.Log
import com.example.core.data.supabase
import com.example.dogprofile.domain.Breed
import com.example.dogprofile.domain.models.Dog
import com.example.dogprofile.domain.models.DogInformation
import com.example.dogprofile.domain.repository.DogRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor() : DogRepository {

    override suspend fun getDogInformation(id: Int): Flow<DogInformation> = flow {
        try {
            Log.d("DogRepositoryImpl", "getDogInformation: $id")
            val columns = Columns.raw("""
                image_url,
                name,
                birthdate,
                description,
                breeds (
                    id,
                    name
                ),
                profiles (
                    id,
                    full_name,
                    city
                )
            """.trimIndent())
            val dogInformation = supabase.from("pets")
                .select(columns = columns) {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingle<DogInformation>()

            val imageUrl = supabase.storage.from("image_upload").publicUrl(dogInformation.image_url?: "")
            dogInformation.image_url = imageUrl

            emit(dogInformation)

        } catch (e: Exception) {
            Log.d("DogRepositoryImpl", "getDogInformation: $e")
            null
        }
    }

    override suspend fun getBreeds(): Flow<List<Breed>> = flow {
        try {
            val breeds = supabase.from("breeds").select(
                columns = Columns.list("id", "name")
            ).decodeList<Breed>()
            emit(breeds)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getDogsUser(): Flow<List<Dog>?> = flow {

        val session = supabase.auth.currentSessionOrNull()

        try {
            Log.d("DogRepositoryImpl", "getDogsUser: $session")
            val columns = Columns.raw("""
                id,
                image_url,
                name,
                breeds (
                    id,
                    name
                ),
                tags
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

//    override suspend fun getImageUrl(url: String): Flow<String> = flow {
//        try {
//            val imageUrl = supabase.storage.from("image_upload").publicUrl(url)
//            emit(imageUrl)
//        } catch (e: Exception) {
//            null
//        }
//    }
}