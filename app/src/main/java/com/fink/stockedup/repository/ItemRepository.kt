package com.fink.stockedup.repository

import androidx.paging.*
import com.fink.stockedup.data.dao.ItemDao
import com.fink.stockedup.data.entity.Item
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {

    // 🔹 Create a new item (STRICT CREATE)
    suspend fun create(item: Item): Long = itemDao.create(item)

    // 🔹 Retrieve a single item by ID
    fun get(id: Long): Flow<Item?> = itemDao.get(id)

    // 🔹 Retrieve all items (Live tracking)
    fun getAll(): Flow<List<Item>> = itemDao.getAll()

    // 🔹 Retrieve all items with paging support (Fix: Converts PagingSource → Flow<PagingData<Item>>)
    fun getAllPaged(): Flow<PagingData<Item>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,               // Adjust for optimal performance
                enablePlaceholders = false   // Improve UI experience
            ),
            pagingSourceFactory = { itemDao.getAllPaged() }
        ).flow

    // 🔹 Update an existing item
    suspend fun update(item: Item): Int = itemDao.update(item)

    // 🔹 Delete a single item by ID
    suspend fun delete(id: Long): Int = itemDao.delete(id)

    // 🔹 Delete all items (Full reset)
    suspend fun deleteAll(): Int = itemDao.deleteAll()
}
