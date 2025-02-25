package com.fink.stockedup.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.fink.stockedup.data.entity.PantryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryDao {
    // 🔹 Insert new item (or update if it exists)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: PantryItem): Long

    // 🔹 Retrieve all items
    @Query("SELECT * FROM pantry_items ORDER BY itemName ASC")
    fun getAllItems(): Flow<List<PantryItem>>

    // 🔹 Retrieve paginated items
    @Query("SELECT * FROM pantry_items ORDER BY itemName ASC")
    fun getPagedItems(): PagingSource<Int, PantryItem>

    // 🔹 Retrieve a single item by ID
    @Query("SELECT * FROM pantry_items WHERE itemId = :id LIMIT 1")
    suspend fun getItemById(id: Long): PantryItem?

    // 🔹 Update an existing item
    @Update
    suspend fun updateItem(item: PantryItem)

    // 🔹 Delete an item
    @Delete
    suspend fun deleteItem(item: PantryItem)

    // 🔹 Delete all expired items
    @Query("DELETE FROM pantry_items WHERE itemExpirationDate < :currentDate")
    suspend fun deleteExpiredItems(currentDate: Long)
}