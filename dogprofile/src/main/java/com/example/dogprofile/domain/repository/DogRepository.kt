package com.example.dogprofile.domain.repository

import com.example.dogprofile.domain.Breed
import com.example.dogprofile.domain.models.Dog
import com.example.dogprofile.domain.models.DogInformation
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDogInformation(id: Int): Flow<DogInformation>
    suspend fun getBreeds(): Flow<List<Breed>>
    suspend fun getDogsUser(): Flow<List<Dog>?>
//    suspend fun getImageUrl(url: String): Flow<String?>
}