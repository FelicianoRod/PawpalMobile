package com.example.authentication.data

import android.net.http.HttpResponseCache.install
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
//import io.github.jan.supabase.gotrue.Auth


//val supabase = createSupabaseClient(
//    supabaseUrl = "https://cwqqdxnttgshxoizlxjc.supabase.co",
//    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImN3cXFkeG50dGdzaHhvaXpseGpjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjE2OTQzNzUsImV4cCI6MjAzNzI3MDM3NX0.y9y4zdNotqPwrAHyuN3qsn9u6wk4cXU4s8eN6t9T_Y4"
//) {
////    install(Auth)
////    install(Postgrest)
//    //install other modules
//}

//fun registerUser(
//    supabase: SupabaseClient,
//    email: String,
//    password: String,
//    callback: (Boolean, String?) -> Unit
//) {
//    supabase.auth.signUpWith(email, password) { result ->
//        if (result.error == null) {
//            callback(true, null)
//        } else {
//            callback(false, result.error.message)
//        }
//
//    }
//}