package com.fink.stockedup.data.database

import androidx.room.TypeConverter
import java.util.Date
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Locale

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

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun fromStringToDate(dateString: String?): Date? {
        return dateString?.let { dateFormat.parse(it) }
    }

    @TypeConverter
    fun fromDateToString(date: Date?): String {
        return date?.let { dateFormat.format(it) } ?: "N/A"
    }
}
