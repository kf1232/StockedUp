package com.fink.stockedup.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormat = SimpleDateFormat("yyyy MM-dd", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("yyyy MM-dd HH:mm", Locale.getDefault())

    /**
     * Converts a timestamp (Long) to "YYYY MM-DD" format.
     */
    fun formatDate(timestamp: Long?): String {
        return timestamp?.let {
            dateFormat.format(Date(it))
        } ?: "N/A"
    }

    /**
     * Converts a timestamp (Long) to "YYYY MM-DD HH:MM" format.
     */
    fun formatDateTime(timestamp: Long?): String {
        return timestamp?.let {
            dateTimeFormat.format(Date(it))
        } ?: "N/A"
    }
}
