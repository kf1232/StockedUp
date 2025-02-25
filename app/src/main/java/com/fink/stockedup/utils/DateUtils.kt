package com.fink.stockedup.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object DateUtils {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun formatDateTime(timestamp: Long): String {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).format(formatter)
    }

    // ✅ Utility function to format date
    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(date)
    }

}
