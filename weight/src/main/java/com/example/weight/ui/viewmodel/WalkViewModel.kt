package com.example.weight.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.weight.domain.model.Weight
import com.example.weight.domain.model.walk.Dog
import com.example.weight.domain.model.walk.Walk
import com.example.weight.domain.repository.WeightRepositoryy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalkViewModel @Inject constructor() : ViewModel() {

    private val _walkHistory = MutableStateFlow<List<Walk>?>(null)
    val walkHistory: MutableStateFlow<List<Walk>?> = _walkHistory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    private val _startDate = MutableStateFlow("")
    val startDate: MutableStateFlow<String> = _startDate

    private val _endDate = MutableStateFlow("")
    val endDate: MutableStateFlow<String> = _endDate

    private val _selectedDates = MutableStateFlow(false)
    val selectedDates: MutableStateFlow<Boolean> = _selectedDates

    fun getWalkHistory(id: Int, startDate: String, endDate: String) {
        viewModelScope.launch {

            _isLoading.value = true

            try {
                val columns = Columns.raw("""
               id,
                name,
                walks!inner (
                    id,
                    day,
                    distance,
                    total_time,
                    notes
                )
            """.trimIndent())

                val dog = supabase.from("pets")
                    .select(columns = columns) {
                        filter {
                            and(
                                referencedTable = "walks",
                            ) {
                                gte("day", startDate)
                                lte("day", endDate)
                            }
                            eq("id", id)
                        }
                    }
                    .decodeSingle<Dog>()
                _isLoading.value = false

                _walkHistory.value = dog.walks

            } catch (e: Exception) {
                null
            }
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