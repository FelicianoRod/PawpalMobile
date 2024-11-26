package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
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

}