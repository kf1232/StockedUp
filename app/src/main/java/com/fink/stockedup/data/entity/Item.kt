package com.fink.stockedup.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long = 0,

    val itemName: String,
    val itemCategory: String,
    val itemUnit: String,

    val itemNotes: String? = null,

    val itemFavorite: Boolean = false,

    val itemCreatedDate: Long = System.currentTimeMillis(),
    val itemLastUpdated: Long = System.currentTimeMillis(),
)
