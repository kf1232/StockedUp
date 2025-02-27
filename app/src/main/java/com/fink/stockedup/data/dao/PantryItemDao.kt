package com.fink.stockedup.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.data.model.PantryItemModel
import com.fink.stockedup.data.model.PantryItemWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryItemDao {

    // 🔹 Create a new pantry item (STRICT CREATE)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(item: PantryItem): Long

    // 🔹 Retrieve a single pantry item with full item details
    @Transaction
    @Query("SELECT * FROM pantry_item WHERE pantryItemId = :id LIMIT 1")
    fun get(id: Long): Flow<PantryItemModel?>

    // 🔹 Retrieve all pantry items with full item details (Live tracking)
    @Transaction
    @Query("SELECT * FROM pantry_item ORDER BY pantryItemCreated DESC")
    fun getAll(): Flow<List<PantryItemModel>>

    // 🔹 Retrieve paginated pantry items with full item details
    @Transaction
    @Query("SELECT * FROM pantry_item ORDER BY pantryItemCreated DESC")
    fun getAllPaged(): PagingSource<Int, PantryItemModel>

    // 🔹 Update an existing pantry item
    @Update
    suspend fun update(item: PantryItem): Int

    // 🔹 Delete a single pantry item by ID
    @Query("DELETE FROM pantry_item WHERE pantryItemId = :id")
    suspend fun delete(id: Long): Int

    // 🔹 Delete all pantry items (Full reset)
    @Query("DELETE FROM pantry_item")
    suspend fun deleteAll(): Int
}
