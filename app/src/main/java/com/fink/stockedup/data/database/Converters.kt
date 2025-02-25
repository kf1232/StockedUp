package com.fink.stockedup.data.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    // 🔹 Convert Date to Long (timestamp) for database storage
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // 🔹 Convert Long (timestamp) back to Date
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}
