package com.example.dogprofile.application

import androidx.lifecycle.ViewModel
import com.example.dogprofile.domain.Dog
import com.example.dogprofile.infrastructure.DogRepository

class GetDog : ViewModel() {

    private val dogRepository = DogRepository()

    suspend fun getDogsUser() : List<Dog>? {
        return dogRepository.getDogsUser()
    }
}
//A