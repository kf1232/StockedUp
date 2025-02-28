package com.fink.stockedup.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.fink.stockedup.data.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // [ C ]
    // Insert new item (Fail if exists)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addItem(item: Item): Long

    // [ R ]
    // Retrieve a single item by ID
    @Query("SELECT * FROM items WHERE itemId = :id LIMIT 1")
    suspend fun getItemById(id: Long): Item?

    // Retrieve all items
    @Query("SELECT * FROM items ORDER BY itemName ASC")
    fun getAllItems(): Flow<List<Item>>

    // Retrieve paginated items
    @Query("SELECT * FROM items ORDER BY itemName ASC")
    fun getPagedItems(): PagingSource<Int, Item>

    // Retrieve items by category
    @Query("SELECT * FROM items WHERE itemCategory = :category ORDER BY itemName ASC")
    fun getAllCategory(category: String): Flow<List<Item>>

    // Retrieve all favorite items
    @Query("SELECT * FROM items WHERE itemFavorite = 1 ORDER BY itemName ASC")
    fun getAllFavorites(): Flow<List<Item>>

    // [ U ]
    // Update an existing item
    @Update
    suspend fun updateItem(item: Item)

    // [ D ]
    // Delete an item by ID
    @Query("DELETE FROM items WHERE itemId = :id")
    suspend fun deleteItemById(id: Long)

    // Delete all items
    @Query("DELETE FROM items")
    suspend fun deleteAll()
}
