package com.example.weight.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.weight.domain.model.vaccine.Vaccine
import com.example.weight.domain.model.vaccine.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class VaccineViewModel @Inject constructor(): ViewModel() {

    private val _vaccineHistory = MutableStateFlow<List<Vaccine>?>(null)
    val vaccineHistory: MutableStateFlow<List<Vaccine>?> = _vaccineHistory

    private val _nextVaccine = MutableStateFlow<List<Vaccine>?>(null)
    val nextVaccine: MutableStateFlow<List<Vaccine>?> = _nextVaccine

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    private val _startDate = MutableStateFlow("")
    val startDate: MutableStateFlow<String> = _startDate

    private val _endDate = MutableStateFlow("")
    val endDate: MutableStateFlow<String> = _endDate

    private val _selectedDates = MutableStateFlow(false)
    val selectedDates: MutableStateFlow<Boolean> = _selectedDates

    fun getVaccineHistory(id: Int, startDate: String, endDate: String) {
        viewModelScope.launch {

            _isLoading.value = true

            try {
                val columns = Columns.raw("""
                    id,
                    name,
                    vaccinations!inner (
                        id,
                        name,
                        date,
                        next_due
                    )
                """.trimIndent())

                val dog = supabase.from("pets")
                    .select(columns = columns) {
                        filter {
                            and(
                                referencedTable = "vaccinations",
                            ) {
                                gte("date", startDate)
                                lte("date", endDate)
                            }
                            eq("id", id)
                        }
                    }
                    .decodeSingle<Dog>()

                _isLoading.value = false

                _vaccineHistory.value = dog.vaccinations

            } catch (e: Exception) {
                Log.e("VaccineViewModel", "Error fetching vaccine history", e)
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

    fun getNextVaccine(id: Int) {

//        val currentTime = System.currentTimeMillis()
//        val date = Date(currentTime)

        val sdf = SimpleDateFormat("yyy-MM-dd")

        // on below line we are creating a variable for
        // current date and time and calling a simple
        // date format in it.
        val currentDate = sdf.format(Date())

        Log.d("Date", currentDate.toString())

        viewModelScope.launch {
            try {
                val columns = Columns.raw("""
                    id,
                    name,
                    vaccinations!inner (
                        id,
                        name,
                        date,
                        next_due
                    )
                """.trimIndent())

                val dog = supabase.from("pets")
                    .select(columns = columns) {
                        filter {
                            and(
                                referencedTable = "vaccinations",
                            ) {
//                                lte("date", endDate)
                                gte("next_due", currentDate)
                            }
                            eq("id", id)
                        }
                    }
                    .decodeSingle<Dog>()
                Log.d("Dog", dog.toString())

                _nextVaccine.value = dog.vaccinations
            } catch (e: Exception) {
                null
            }
        }
    }
}