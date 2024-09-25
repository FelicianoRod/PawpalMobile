package com.example.dogprofile.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.dogprofile.domain.Dog
import com.example.dogprofile.infrastructure.DogRepository
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch

class DogRegistrar : ViewModel() {
    private val dogRepository = DogRepository()

    fun registerDog(
        name: String,
        isOwner: Boolean,
        birthdate: String,
        gender: String,
        breedId: Int,
        description: String,
        weight: Double,
        tags: List<String>,
        imageUrl: String
    ) {

        val session = supabase.auth.currentSessionOrNull()

        val dog = Dog(
            id = 0,
            profile_id = session?.user?.id ?: "",
            breed_id = breedId,
            name = name,
            birthdate = birthdate,
            gender = gender,
            create_at = "",
            image_url = imageUrl,
            tags = tags.joinToString(","),
            owner_name = session?.user?.email ?: "",
            description = description,
            health = "",
            weight = weight
        )

        viewModelScope.launch {
            val result = dogRepository.addDog(dog)
        }
    }
}