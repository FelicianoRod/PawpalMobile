package com.example.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.model.Walk
import com.example.home.domain.repository.WalkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalkViewModel @Inject constructor(
    private val walkRepository: WalkRepository
) : ViewModel() {

    private val _walks = MutableStateFlow<List<Walk>?>(null)
    val walks: MutableStateFlow<List<Walk>?> = _walks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: MutableStateFlow<Boolean> = _isLoading


    fun getWalks(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            walkRepository.getWalks(id)
                .collect { dogWalk ->
                    _walks.value = dogWalk.walks
                }

            _isLoading.value = false
        }
    }
}

// TODO: Implement the ViewModel
// Implementar (cuando no se ha seleccionado con perrito
// Cuándo el perrito no tiene registro de paseos
// Y cuándo mostras sus paseos
// Añadir al Module para que hilt pueda injectar walkRepository