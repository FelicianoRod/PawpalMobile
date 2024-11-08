package com.example.home.domain.repository

import com.example.home.domain.model.DogWalk
import kotlinx.coroutines.flow.Flow

interface WalkRepository {
    suspend fun getWalks(id: Int) : Flow<DogWalk>
}