package com.example.core.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimestamptzFormatter {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedDate(timestamp: String): String {
        val dataTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME)
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy")
        val formattedDate = dataTime.format(dateTimeFormatter)
        return formattedDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedDateTime(timestamp: String): String {
        val dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME)
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val formattedDateTime = dateTime.format(dateTimeFormatter)

        return formattedDateTime
    }

    @SuppressLint("NewApi")
    fun formattedDate(date: String): String {
        val localDate = LocalDate.parse(date)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return localDate.format(formatter)
    }

}