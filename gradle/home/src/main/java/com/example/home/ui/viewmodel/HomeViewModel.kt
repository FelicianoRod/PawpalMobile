package com.example.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ui.repository.StateRepository
import com.example.home.domain.model.Dog
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val stateRepository: StateRepository
) : ViewModel() {

    private val _dogs = MutableStateFlow<List<Dog>?>(null)
    val dogs: MutableStateFlow<List<Dog>?> = _dogs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _selectedDog = MutableStateFlow<Dog?>(null)
    val selectedDog: StateFlow<Dog?> = _selectedDog

    fun getDogs() {
        viewModelScope.launch {
            _isLoading.value = true

            homeRepository.getListDogs()
                .collect { dogs ->
                    _dogs.value = dogs
                }

            _isLoading.value = false
        }
    }

    fun selectDog(dog: Dog?) {
        stateRepository.selectedDogId.value = dog?.id
        _selectedDog.value = dog
    }
}