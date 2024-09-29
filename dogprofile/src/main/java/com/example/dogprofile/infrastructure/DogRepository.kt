package com.example.dogprofile.infrastructure

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.dogprofile.domain.Breed
import com.example.dogprofile.domain.Dog
import com.example.userprofile.data.viewmodel.ProfileViewModel
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogRepository : ViewModel() {

    suspend fun getBreeds() : List<Breed>? {
        return try {
            Log.d("DogRepository", "Breeds fetched successfully")

            supabase.from("breeds").select(
                columns = Columns.list("id", "name")
            ).decodeList<Breed>()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getDogsUser(): List<Dog>? {

        val session = supabase.auth.currentSessionOrNull()

        return try {
            supabase.from("pets")
                .select() {
                    filter {
                        eq("profile_id", session?.user?.id ?: "")
                    }
                }.decodeList<Dog>()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addDog(dog: Dog): Boolean {

        Log.d("DogRepository", "Dog added successfully")

        return withContext(Dispatchers.IO) {
            try {
                supabase.from("pets").insert(dog)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}