//package com.example.core.utils
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import kotlinx.datetime.Instant
//import kotlinx.datetime.LocalDateTime
//import kotlinx.datetime.TimeZone
//import kotlinx.datetime.toLocalDateTime
//import java.time.OffsetDateTime
//import java.time.format.DateTimeFormatter
//
//@RequiresApi(Build.VERSION_CODES.O)
//fun monthDayHourMinute(date: String) : String {
//
//    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//    val localDateTime = LocalDateTime.parse(text, pattern)
//
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX")
//    val dateTime = OffsetDateTime.parse(date, formatter)
//
//    // Obtener el mes, día, hora y minuto
//    val month = dateTime.monthValue       // Mes (número, 10 para octubre)
//    val day = dateTime.dayOfMonth         // Día del mes
//    val hour = dateTime.hour              // Hora (formato de 24 horas)
//    val minute = dateTime.minute          // Minuto
//
//    return "$month/$day - $hour:$minute"
//
//}
//
////object DateConverter {
////    @RequiresApi(Build.VERSION_CODES.O)
////    fun millisToFormattedDate(millis: Long, pattern: String = "yyyy-MM-dd"): String {
////        val instant = Instant.fromEpochMilliseconds(millis)
////        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
////
////        val year = localDateTime.year
////        val month = localDateTime.monthNumber
////        val day = localDateTime.date
////
////        return "$day"
////    }
////}