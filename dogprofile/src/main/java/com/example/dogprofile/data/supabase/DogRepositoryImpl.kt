package com.example.dogprofile.data.supabase

import android.util.Log
import com.example.core.data.supabase
import com.example.dogprofile.domain.Breed
import com.example.dogprofile.domain.Dog
import com.example.dogprofile.domain.models.DogInformation
import com.example.dogprofile.domain.repository.DogRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogRepositoryImpl : DogRepository {

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

//            val modifiedDogInformation = dogInformation.copy(
//                image_url = imageUrl
//            )

            emit(dogInformation)
//            supabase.from("pets")
//                .select() {
//                    filter {
//                        eq("id", id)
//                    }
//                }.decodeAs<Dog>()

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
            val dogs = supabase.from("pets")
                .select() {
                    filter {
                        eq("profile_id", session?.user?.id ?: "")
                    }
                }.decodeList<Dog>()
            emit(dogs)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getImageUrl(url: String): Flow<String> = flow {
        try {
            val imageUrl = supabase.storage.from("image_upload").publicUrl(url)
            emit(imageUrl)
        } catch (e: Exception) {
            null
        }
    }
}