package com.example.dogprofile.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.dogprofile.domain.Breed
import com.example.dogprofile.domain.Dog
import com.example.dogprofile.infrastructure.DogRepository
import com.example.userprofile.data.viewmodel.ProfileViewModel
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogRegistrar : ViewModel() {

    private val dogRepository = DogRepository()

    private val profileViewModel = ProfileViewModel()

    suspend fun getBreeds(): List<Breed>? {
        return withContext(Dispatchers.IO) {
            dogRepository.getBreeds()
        }
    }

    suspend fun registerDog(
        name: String,
        isOwner: Boolean,
        birthdate: String,
        gender: Boolean,
        breedId: Int,
        description: String,
        weight: Double,
        tags: List<String>,
        imageUrl: String
    ) : Boolean {

        val session = supabase.auth.currentSessionOrNull()

//        viewModelScope.launch {
            val dog = withContext(Dispatchers.IO) {
                val ownerName = if (isOwner) {
                    profileViewModel.getProfile()?.full_name
                } else {
                    null
                }

                val genderText = if (gender) {
                    "Male"
                } else {
                    "Female"
                }

//                var onwerName: String? = null
//                if (isOwner) {
//                    val profile = profileViewModel.getProfile()
//                    ownerName = profile?.full_name
//                }
                Dog(
                    name = name,
                    owner_name = ownerName,
                    profile_id = session?.user?.id ?: "",
                    birthdate = birthdate,
                    gender = genderText,
                    breed_id = breedId,
                    description = description,
                    weight = weight,
                )
            }
            val result = dogRepository.addDog(dog)
        return result
//        }


//        val dog = Dog(
//            id = 100,
//            profile_id = session?.user?.id ?: "",
//            breed_id = 4,
//            name = name,
////            birthdate = "",
//            gender = "Male",
////            create_at = "",
////            image_url = "",
////            tags = tags.joinToString(","),
////            tags = "",
////            owner_name = session?.user?.email ?: "",
////            owner_name = "Emilio",
//            description = description,
////            health = "",
//            weight = 32.3
//        )

//        viewModelScope.launch {
//        }
    }
}