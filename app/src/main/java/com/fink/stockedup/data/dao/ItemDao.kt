package com.fink.stockedup.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.fink.stockedup.data.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    // Create a new item (STRICT CREATE: Fails if item already exists)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(item: Item): Long

    // Retrieve a single item by ID
    @Query("SELECT * FROM item WHERE itemId = :id LIMIT 1")
    fun get(id: Long): Flow<Item?>

    // Retrieve all items (Live tracking)
    @Query("SELECT * FROM item ORDER BY itemName ASC")
    fun getAll(): Flow<List<Item>>

    // Retrieve all items with paging support
    @Query("SELECT * FROM item ORDER BY itemName ASC")
    fun getAllPaged(): PagingSource<Int, Item>

    @Update
    suspend fun update(item: Item): Int

    // Delete a single item by ID
    @Query("DELETE FROM item WHERE itemId = :id")
    suspend fun delete(id: Long): Int

    // Delete all items (Full reset)
    @Query("DELETE FROM item")
    suspend fun deleteAll(): Int
}
