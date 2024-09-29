package com.example.dogprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogprofile.application.DogRegistrar
import com.example.dogprofile.ui.models.DogFormStateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun onGenderChanged(gender: Boolean) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(gender = gender)
    }

    fun onBreedIdSelected(breedId: Int) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(breedId = breedId)
    }

    fun onDescriptionChanged(description: String) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(description = description)
    }

    fun onWeightChanged(weight: Double) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(weight = weight)
    }

    fun onTagsChanged(tags: List<String>) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(tags = tags)
    }

    fun onImageUrlChanged(imageUrl: String) {
        val currentState = _dogFormState.value
        _dogFormState.value = currentState.copy(imageUrl = imageUrl)
    }

    fun getBreedsList() {
        viewModelScope.launch {
            val breeds = dogRegistrar.getBreeds()
            println(breeds?.size)
            if (breeds != null) {
                _dogFormState.value = _dogFormState.value.copy(breeds = breeds)
            }
        }
    }

//    fun addDogOnClick() {
//        addDog()
//    }

    fun addDog() {
        val currentState = _dogFormState.value

        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                dogRegistrar.registerDog(
                    name = currentState.name,
                    isOwner = currentState.isOwner,
                    birthdate = currentState.birthdate,
                    gender = currentState.gender,
                    breedId = currentState.breedId,
                    description = currentState.description,
                    weight = currentState.weight,
                    tags = currentState.tags,
                    imageUrl = currentState.imageUrl
                )
            }
        }
    }


}