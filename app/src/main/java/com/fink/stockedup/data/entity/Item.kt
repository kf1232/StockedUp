package com.fink.stockedup.data.entity

import androidx.room.*

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true) val itemId: Long = 0,
    val itemName: String,
    val itemUnit: String?,
    val itemCategory: String?,
    val itemCreated: Long = System.currentTimeMillis(),
    val itemUpdated: Long = System.currentTimeMillis(),
    val itemFavorite: Boolean = false
)

// TODO investigate itemName being a unique value
// TODO standardize itemUnit across all entity fields.