package com.example.weight.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weight.domain.model.Weight
import com.example.weight.domain.repository.WeightRepositoryy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val weightRepository: WeightRepositoryy,
) : ViewModel() {

    private val _weightHistory = MutableStateFlow<List<Weight>?>(null)
    val weightHistory: MutableStateFlow<List<Weight>?> = _weightHistory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    private val _startDate = MutableStateFlow("")
    val startDate: MutableStateFlow<String> = _startDate

    private val _endDate = MutableStateFlow("")
    val endDate: MutableStateFlow<String> = _endDate

    private val _selectedDates = MutableStateFlow(false)
    val selectedDates: MutableStateFlow<Boolean> = _selectedDates

//    init {
//        combine(_startDate, _endDate) { startDate, endDate ->
//            startDate.isNotEmpty() && endDate.isNotEmpty()
//        }.map {isSelectedDates ->
//            _selectedDates.value = isSelectedDates
//        }
//    }

    fun getWeightHistory(id: Int, startDate: String, endDate: String) {
        viewModelScope.launch {

            _isLoading.value = true

            weightRepository.getWeight(id, startDate, endDate)
                .collect { weightHistory ->
                    _weightHistory.value = weightHistory
                }

            _isLoading.value = false
        }
    }

    fun onStartDateChanged(startDate: String) {
        _startDate.value = startDate
        onSelectedDatesChanged()
    }

    fun onEndDateChanged(endDate: String) {
        _endDate.value = endDate
        onSelectedDatesChanged()
    }

    private fun onSelectedDatesChanged() {
        if (_startDate.value.isNotEmpty() && _endDate.value.isNotEmpty()) {
            _selectedDates.value = true
        }
    }

}