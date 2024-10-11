package com.example.dogprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogprofile.domain.models.DogInformation
import com.example.dogprofile.domain.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogInformationViewModel(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _dogInformation = MutableStateFlow<DogInformation?>(null)
    val dogInformation: StateFlow<DogInformation?> = _dogInformation

//    init {
//        getDogInformation()
//    }

    fun getDogInformation(dogId: Int) {
        viewModelScope.launch {
            dogRepository.getDogInformation(dogId)
                .collect { dogInformation ->
                    _dogInformation.value = dogInformation
                }
        }
    }

}