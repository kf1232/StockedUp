package com.fink.stockedup.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "pantry_items")
data class PantryItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long = 0,
    val itemName: String,
    val itemQuantity: Int,
    val itemCategory: String,
    val itemExpirationDate: Date?,
    val itemStorageLocation: String,
    val itemUnit: String,
    val itemLastUpdated: Long = System.currentTimeMillis(),
    val itemNotes: String? = null,
    val itemFavorite: Boolean = false
)
