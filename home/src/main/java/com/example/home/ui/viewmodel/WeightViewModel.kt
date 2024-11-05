package com.example.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.model.Weight
import com.example.home.domain.repository.HomeRepository
import com.example.home.domain.repository.WeightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val weightRepository: WeightRepository,
) : ViewModel() {

    private val _weightHistory = MutableStateFlow<List<Weight>?>(null)
    val weightHistory: MutableStateFlow<List<Weight>?> = _weightHistory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    // TODO: Implement the ViewModel
    // Llamar la función de abajo
    // Quite el HomeViewModel
    fun getWeightHistory(id: Int) {
        viewModelScope.launch {

            _isLoading.value = true

            weightRepository.getWeight(id)
                .collect { weightHistory ->
                    _weightHistory.value = weightHistory
                }

            _isLoading.value = false
        }
    }
}