package com.example.core.ui.repository

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StateRepository @Inject constructor() {

    var selectedDogId = MutableStateFlow<Int?>(null)
}