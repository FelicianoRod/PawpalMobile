package com.example.dogprofile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogprofile.application.GetDog
import com.example.dogprofile.domain.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogStateViewModel() : ViewModel() {

    private val getDogs = GetDog()

    private val _dogList = MutableStateFlow<List<Dog>>(emptyList())
    val dogList: MutableStateFlow<List<Dog>> = _dogList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun getDogsUserList() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _isLoading.value = true

                val dogs = getDogs.getDogsUser()
                if (dogs != null) {
                    _dogList.value = dogs
                }

                _isLoading.value = false

            }
        }
    }
}