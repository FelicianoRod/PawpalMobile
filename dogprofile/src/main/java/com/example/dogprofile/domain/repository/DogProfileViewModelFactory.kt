package com.example.dogprofile.domain.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dogprofile.ui.viewmodel.DogProfileViewModel

class DogProfileViewModelFactory (
    private val dogRepository: DogRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogProfileViewModel::class.java)) {
            return DogProfileViewModel(dogRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}