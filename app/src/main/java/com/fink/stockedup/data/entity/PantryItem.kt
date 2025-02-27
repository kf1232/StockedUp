package com.fink.stockedup.data.entity

import androidx.room.*

@Entity(
    tableName = "pantry_item",
    foreignKeys = [ForeignKey(
        entity = Item::class,
        parentColumns = ["itemId"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.RESTRICT // Cannot delete item until cleared fully from pantry_item
    )],
    indices = [Index(value = ["itemId"])]
)
data class PantryItem(
    @PrimaryKey(autoGenerate = true)
    val pantryItemId: Long = 0,
    val itemId: Long,
    val pantryItemQuantity: Double,
    val pantryItemStorageLocation: String?,
    val pantryItemNote: String?,
    val pantryItemExpirationDate: Long?,
    val pantryItemCreated: Long = System.currentTimeMillis(),
    val pantryItemUpdated: Long = System.currentTimeMillis(),
    val pantryItemFavorite: Boolean = false
)


// TODO itemQuantity could become a DOUBLE value to better manage / count on-hand products.
// TODO set itemName to UNIQUE, unclear if we want to allow multiple records of the same thing.
// TODO set itemStorageLocation to an ENUM or set of fixed values
// TODO itemUnit should be a fixed set of measurement types


// TODO I wouldn't want this to delete in cascade.  There should be an error asking the user to clear the pantry first.