package com.example.authentication.data.repository

import android.util.Log
import com.example.authentication.data.models.User
import com.example.authentication.domain.repository.AuthRepository
import com.example.core.data.supabase
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl() : AuthRepository {

    override suspend fun signUp(user: User) {
        withContext(Dispatchers.IO) {
            try {
                val user = supabase.auth.signUpWith(Email) {
                    email = user.email
                    password = user.password
                }
            } catch (e: Exception) {
                Log.d("AuthRepositoryImpl", "signUp: $e")
            }
        }
    }

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