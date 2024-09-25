package com.example.dogprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dogprofile.application.DogRegistrar
import com.example.dogprofile.ui.models.DogFormStateModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddDogState() : ViewModel() {

    private val dogRegistrar = DogRegistrar()

    private val _dogFormState = MutableStateFlow(DogFormStateModel())
    val dogFormState: StateFlow<DogFormStateModel> = _dogFormState.asStateFlow()

    fun onNameChanged(name: String) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(name = name)
    }

    fun onIsOwnerChanged(isOwner: Boolean) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(isOwner = isOwner)
    }

    fun onBirthdateChanged(birthdate: String) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(birthdate = birthdate)
    }


}