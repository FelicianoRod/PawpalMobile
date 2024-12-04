package com.example.dogprofile.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogprofile.R
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

    private val _showMessage = MutableStateFlow(false)
    val showMessage: StateFlow<Boolean> = _showMessage.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()


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

    fun addDog(context: Context) {
        val currentState = _dogFormState.value

        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = dogRegistrar.registerDog(
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
                if (result) {
                    _message.value = "Mascota registrada exitosamente."
                    sendNotification(context, currentState.name)
                } else {
                    _message.value = "Error al agregar la mascota."
                }
                _showMessage.value = true


            }
        }
    }

    @SuppressLint("NewApi")
    fun sendNotification(context: Context, name: String) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = Notification.Builder(context, "my_channel")
            .setContentTitle("Nueva mascota registrada")
            .setContentText("Cuidemos de $name!")
            .setSmallIcon(R.drawable.pawpal_logo)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(1, notification)
    }


}