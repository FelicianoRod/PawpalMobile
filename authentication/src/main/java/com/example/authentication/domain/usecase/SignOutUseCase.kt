package com.example.authentication.domain.usecase

import com.example.authentication.domain.repository.AuthRepository

class SignOutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.signOut()
    }
}