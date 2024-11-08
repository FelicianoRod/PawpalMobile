package com.example.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.model.Nutrition
import com.example.home.domain.repository.NutritionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val nutritionRepository: NutritionRepository
) : ViewModel() {

    private val _nutritionHistory = MutableStateFlow<List<Nutrition>?>(null)
    val nutritionHistory: MutableStateFlow<List<Nutrition>?> = _nutritionHistory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    fun getNutritionHistory(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            nutritionRepository.getNutrition(id)
                .collect { dogNutrition ->
                    _nutritionHistory.value = dogNutrition.pet_nutrition
                }

            _isLoading.value = false
        }
    }
}