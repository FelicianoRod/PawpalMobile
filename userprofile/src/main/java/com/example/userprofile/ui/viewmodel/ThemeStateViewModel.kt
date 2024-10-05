package com.example.userprofile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThemeStateViewModel : ViewModel() {
    private val _themeMode = MutableLiveData<ThemeMode>(ThemeMode.System)
    val themeMode: LiveData<ThemeMode> = _themeMode

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
    }
}

enum class ThemeMode {
    Light,
    Dark,
    System,
    Dynamic
}