package com.example.dogprofile.infrastructure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.dogprofile.domain.Dog
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogRepository : ViewModel() {

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

        return withContext(Dispatchers.IO) {
            try {
                supabase.from("pets").insert(Dog)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}