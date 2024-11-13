package com.example.core.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

fun getDayOfWeek(dateString: String): String {
    // Definir el formato de la fecha de entrada usando DateTimeFormatter
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    // Definir un array con los días de la semana en español
    val weekDays = arrayOf("D", "L", "M", "M", "J", "V", "S")

    return try {
        // Parsear la fecha de entrada a OffsetDateTime
        val date = OffsetDateTime.parse(dateString, inputFormat)

        // Obtener el día de la semana en base al valor de fecha (1: lunes, 7: domingo)
        val dayOfWeek = date.dayOfWeek.value // Esto da un valor del 1 (lunes) al 7 (domingo)

        // Mapear el valor de día de la semana al arreglo personalizado
        weekDays[dayOfWeek % 7]  // % 7 para ajustar domingo a 0 en el array
    } catch (e: Exception) {
        println("Error al obtener el día de la semana: ${e.message}")
        ""
    }
}