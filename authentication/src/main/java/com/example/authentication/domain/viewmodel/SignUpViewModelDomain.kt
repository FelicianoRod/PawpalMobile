package com.example.authentication.domain.viewmodel

import android.util.Log
import com.example.core.data.supabase
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

suspend fun signUpWithEmail(email: String, password: String) {
    try {
        val user = supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    } catch (e: Exception) {
        Log.e("signUpWithEmail", "Error: ", e)
    }
}


