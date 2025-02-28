package com.fink.stockedup.data.dao

import androidx.room.*
import com.fink.stockedup.data.entity.PantryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryItemDao {

		// [ C ] CREATE
				// Insert a new pantry item, ensuring it references an existing `itemId`
				@Insert(onConflict = OnConflictStrategy.ABORT)
				suspend fun addPantryItem(pantryItem: PantryItem): Long



		// [ R ] READ
				// Retrieve a pantry item by ID
				@Query("SELECT * FROM pantry_items WHERE pantryItemId = :id LIMIT 1")
				suspend fun getPantryItemById(id: Long): PantryItem?

				// Retrieve all pantry items linked to a specific `itemId`
				@Query("SELECT * FROM pantry_items WHERE itemId = :itemId ORDER BY pantryItemLastUpdated DESC")
				fun getPantryItemsByItemId(itemId: Long): Flow<List<PantryItem>>

				// Retrieve all pantry items (live updates)
				@Query("SELECT * FROM pantry_items ORDER BY pantryItemLastUpdated DESC")
				fun getAllPantryItems(): Flow<List<PantryItem>>



		// [ U ] UPDATE
				// Update an existing pantry item
				@Update
				suspend fun updatePantryItem(pantryItem: PantryItem)



		// [ D ] DELETE
				// Delete a specific pantry item (does NOT delete from `items`)
				@Query("DELETE FROM pantry_items WHERE pantryItemId = :id")
				suspend fun deletePantryItemById(id: Long)

				// Delete all pantry items (does NOT delete from `items`)
				@Query("DELETE FROM pantry_items")
				suspend fun deleteAllPantryItems()
}
