package com.example.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class SelectedDogViewModel @Inject constructor() : ViewModel() {

    private val _selectedDog = MutableStateFlow<Int?>(null)
    val selectedDog: StateFlow<Int?> = _selectedDog

    fun selectDog(id: Int) {
        _selectedDog.value = id
    }

}