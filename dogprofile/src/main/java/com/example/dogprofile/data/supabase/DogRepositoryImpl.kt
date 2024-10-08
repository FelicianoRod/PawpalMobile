package com.example.dogprofile.data.supabase

import android.util.Log
import com.example.core.data.supabase
import com.example.dogprofile.domain.Dog
import com.example.dogprofile.domain.models.DogInformation
import com.example.dogprofile.domain.repository.DogRepository
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogRepositoryImpl : DogRepository {

    override suspend fun getDogInformation(id: Int): Flow<DogInformation> = flow {
        try {
            Log.d("DogRepositoryImpl", "getDogInformation: $id")
            val columns = Columns.raw("""
                image_url,
                name,
                breeds (
                    id,
                    name
                ),
                birthdate,
                profiles (
                    id,
                    full_name,
                    city
                ),
                description
            """.trimIndent())
            val dogInformation = supabase.from("pets")
                .select(
                    columns = columns
                ).decodeSingle<DogInformation>()
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
}