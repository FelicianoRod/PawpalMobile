package com.example.core.ui.viewmodel

import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor() : ViewModel() {

    private val _drawerState = mutableStateOf(DrawerValue.Closed)
    val drawerState: State<DrawerValue> = _drawerState

    fun toggleDrawer() {
        _drawerState.value = if (_drawerState.value == DrawerValue.Closed) DrawerValue.Open else DrawerValue.Closed
    }

}