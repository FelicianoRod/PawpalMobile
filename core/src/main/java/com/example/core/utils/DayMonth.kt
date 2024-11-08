package com.example.core.utils

import android.util.Log
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun dayMonth(dateString: String): String {

    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale("es", "ES"))

    return try {
        val date = OffsetDateTime.parse(dateString, inputFormat)

        val day = date.getDayOfMonth()
        val month = date.getMonthValue()

        "$day/$month"

    } catch (e: Exception) {
        Log.e("Error", "Error al obtener el día de la semana: ${e.message}")
        ""
    }
}