package com.example.userprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userprofile.data.viewmodel.ProfileViewModel
import com.example.userprofile.ui.models.ProfileStateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileStateViewModel : ViewModel() {

    val profileViewModel = ProfileViewModel()

    private val _profileState = MutableStateFlow(ProfileStateModel())
    val profileState: StateFlow<ProfileStateModel> = _profileState.asStateFlow()

    private val _showMessage = MutableStateFlow(false)
    val showMessage: StateFlow<Boolean> = _showMessage.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    fun onNameChanged(name: String) {
        val currentState = _profileState.value
        _profileState.value = currentState.copy(name = name)
    }

    fun onBirthDateChanged(birthDate: String) {
        val currentState = _profileState.value
        _profileState.value = currentState.copy(birthDate = birthDate)
    }

    fun onCityChanged(city: String) {
        val currentState = _profileState.value
        _profileState.value = currentState.copy(city = city)
    }

    fun onBiographyChanged(biography: String) {
        val currentState = _profileState.value
        _profileState.value = currentState.copy(biography = biography)
    }

    fun onPhoneChanged(phone: String) {
        val currentState = _profileState.value
        _profileState.value = currentState.copy(phone = phone)
    }

    fun getProfileUi() {
        getProfileState()
    }

    private fun getProfileState() {
        viewModelScope.launch {
            val user = profileViewModel.getProfile()

            if (user != null) {
                val currentState = _profileState.value
                _profileState.value = currentState.copy(
                    name = user.full_name?: "" ,
                    birthDate = user.date_of_birth ?: "",
                    city = user.city ?: "",
                    biography = user.biography ?: "",
                    phone = user.cellphone ?: ""
                )
            } else {
                val message = "No se pudo obtener tu información de perfil.";
            }
        }
    }

    fun updateProfileClickable() {
        updateProfile()
    }

    private fun updateProfile() {
        val currentState = _profileState.value

        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = profileViewModel.updateProfileSupabase(
                    name = currentState.name,
                    birthDate = currentState.birthDate,
                    city =  currentState.city,
                    biography = currentState.biography,
                    phone =  currentState.phone,
                    onSuccess = {
                        val message = "Tu perfil ha sido actualizado"
                    },
                    onError = {
                        val message = "No se pudo actualizar tu perfil"
                    }
                )
                if (result) {
                    _message.value = "Tu perfil ha sido actualizado"
                } else {
                    _message.value = "No se pudo actualizar tu perfil"
                }
                _showMessage.value = true
            }
        }

        val message = "Tu perfil ha sido actualizado"
    }

}