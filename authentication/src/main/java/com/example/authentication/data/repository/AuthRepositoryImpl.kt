package com.example.authentication.data.repository

import android.util.Log
import com.example.authentication.domain.repository.AuthRepository
import com.example.core.data.supabase
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl() : AuthRepository {

    override suspend fun signOut() {
        withContext(Dispatchers.IO) {
            try {
                supabase.auth.signOut()
            } catch (e: Exception) {
                Log.d("AuthRepositoryImpl", "signOut: $e")
            }
        }
    }

}