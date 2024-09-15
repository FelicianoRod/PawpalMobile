package com.example.authentication.data.repository.supabase

import com.example.authentication.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
//import io.github.jan.supabase.gotrue.GoTrue

val supabase = createSupabaseClient(
    supabaseUrl = "https://zptmnfxwynrgesibgvqa.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpwdG1uZnh3eW5yZ2VzaWJndnFhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTY2MTMwMzEsImV4cCI6MjAzMjE4OTAzMX0.0tm7zW2yZ9OoXJZVcG5WWpiiLa5rIfaggZmoI9hWavA"
) {
    install(Auth)
    install(Postgrest)
//    install(GoTrue)
}

//val auth = supabase.auth