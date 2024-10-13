package com.example.dogprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogprofile.domain.models.Dog
import com.example.dogprofile.domain.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogProfileViewModel(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _dogList = MutableStateFlow<List<Dog>?>(emptyList())
    val dogList: MutableStateFlow<List<Dog>?> get() = _dogList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun getDogsUserList() {
        viewModelScope.launch {
            _isLoading.value = true

            dogRepository.getDogsUser()
                .collect { dogs ->
                    _dogList.value = dogs
                }

            _isLoading.value = false
        }
    }
}