package com.fink.stockedup.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
		tableName = "pantry_items",
		foreignKeys = [
				ForeignKey(
						entity = Item::class,
						parentColumns = ["itemId"],
						childColumns = ["itemId"],
						onDelete = ForeignKey.NO_ACTION
				)
		],
		indices = [Index(value = ["itemId"])]
)
data class PantryItem(
		@PrimaryKey(autoGenerate = true)
		val pantryItemId: Long = 0,

		val itemId: Long, // Foreign key reference to `items`, now indexed

		// Optional Override Fields
		val pantryItemName: String? = null, // Overrides `itemName`
		val pantryItemCategory: String? = null, // Overrides `itemCategory`
		val pantryItemUnit: String? = null, // Overrides `itemUnit`

		val pantryItemQuantity: Int, // Tracks inventory count
		val pantryItemNotes: String? = null, // Custom notes
		val pantryItemFavorite: Boolean? = null, // Overrides `itemFavorite`

		// Expiration and Tracking Data
		val pantryItemExpirationDate: Long = System.currentTimeMillis(),
		val pantryItemCreatedDate: Long = System.currentTimeMillis(),
		val pantryItemLastUpdated: Long = System.currentTimeMillis()
)
