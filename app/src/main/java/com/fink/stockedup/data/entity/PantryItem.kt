package com.fink.stockedup.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "pantry_items")
data class PantryItem(
    @PrimaryKey(autoGenerate = true) val item_id: Long = 0,
    val item_name: String,
    val item_quantity: Int,
    val item_category: String,
    val item_expiration_date: Date?,
    val item_storage_location: String,
    val item_unit: String,
    val item_last_updated: Long = System.currentTimeMillis(),
    val item_notes: String? = null,
    val item_favorite: Boolean = false
)
