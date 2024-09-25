package com.example.authentication.domain.viewmodel

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.authentication.MainActivity
import com.example.core.data.supabase
import com.example.core.domain.saveToken
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

suspend fun signInWithEmailDomain(email: String, password: String, navController: NavController) {
    try {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }

        val session = supabase.auth.currentSessionOrNull()

//        if (session == null) {
//            throw Exception("No se pudo iniciar sesión")
//        }
//        saveToken(MainActivity(), session?.accessToken ?: "")


        navController.navigate("home")

    } catch (e: Exception) {
        Log.e("signInWithEmailDomain", "Error: ", e)
    }

}