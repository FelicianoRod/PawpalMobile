package com.example.dogprofile.domain.repository

import com.example.dogprofile.domain.Dog
import com.example.dogprofile.domain.models.DogInformation
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDogInformation(id: Int): Flow<DogInformation>
}