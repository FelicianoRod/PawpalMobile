package com.example.userprofile.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.supabase
import com.example.userprofile.data.model.Profile
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {

    suspend fun getProfile(): Profile? {
        val session = supabase.auth.currentSessionOrNull()

        if (session != null) {
            return try {
                Log.e("Profile-Feliciano", "Succesfully")
                supabase.from("profiles")
                    .select() {
                        filter {
                            eq("id", session.user?.id ?: "")
                        }
                    }.decodeSingle<Profile>()
            } catch (e: Exception) {
                Log.e("Profile-Feliciano", "Error: ", e)
                null
            }
        } else {
            Log.e("Profile-Feliciano", "No session")
            return null;
        }
    }

    suspend fun updateProfileSupabase(
        name: String,
        birthDate: String,
        city: String,
        biography: String,
        phone: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val session = supabase.auth.currentSessionOrNull()

                try {
                    if (session != null) {
                        supabase.from("profiles").update(
                            {
                                set("full_name", name)
                                set("date_of_birth", birthDate)
                                set("city", city)
                                set("biography", biography)
//                                set("phone", phone)
                            }
                        ) {
                            filter {
                                eq("id", session.user?.id ?: "")
                            }
                        }
                        withContext(Dispatchers.Main) {
                            onSuccess("Perfil actualizado")
                        }
                    } else {
                        Log.w("Profile-Feliciano", "No session")
                        withContext(Dispatchers.Main) {
                            onError("No existe una sesión")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Profile-Feliciano", "Error: ", e)
                    withContext(Dispatchers.Main) {
                        onError("No se pudo actualizar tu perfil")
                    }
                }
            }
        }
    }
}


