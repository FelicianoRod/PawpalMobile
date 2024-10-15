package com.example.authentication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.domain.usecase.SignOutUseCase
import kotlinx.coroutines.launch

class SignOutViewModel(
    private val signOutUseCase: SignOutUseCase
): ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            try {
                signOutUseCase()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}