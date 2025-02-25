package com.fink.stockedup.data.database

import androidx.room.TypeConverter
import java.util.Date
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, type)
        }
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String {
        return gson.toJson(list)
    }

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
