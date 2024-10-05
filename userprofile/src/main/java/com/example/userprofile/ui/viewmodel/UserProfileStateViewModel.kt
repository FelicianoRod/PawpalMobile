package com.example.userprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userprofile.data.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserProfileStateViewModel : ViewModel() {

     val profileViewModel: ProfileViewModel = ProfileViewModel()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _biography = MutableStateFlow("")
    val biography: StateFlow<String> = _biography.asStateFlow()

    private val _avatarUrl = MutableStateFlow("")
    val avatarUrl: StateFlow<String> = _avatarUrl.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getUserProfileState() {
        viewModelScope.launch {
            _isLoading.value = true

            val user = profileViewModel.getProfile()

            if (user != null) {
                _name.value = user.full_name ?: "..."
                _biography.value = user.biography ?: "..."
                _avatarUrl.value = user.avatar_url ?: ""
            } else {
                val message = "No se pudo obtener tu información de perfil.";
            }
            _isLoading.value = false
        }
    }
}