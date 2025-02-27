package com.fink.stockedup.repository

import androidx.paging.*
import com.fink.stockedup.data.dao.PantryItemDao
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.data.model.PantryItemModel
import kotlinx.coroutines.flow.Flow

class PantryItemRepository(private val pantryItemDao: PantryItemDao) {

    // 🔹 Create a new pantry item (STRICT CREATE)
    suspend fun create(item: PantryItem): Long = pantryItemDao.create(item)

    // 🔹 Retrieve a single pantry item (WITH FULL ITEM DETAILS)
    fun get(id: Long): Flow<PantryItemModel?> = pantryItemDao.get(id)

    // 🔹 Retrieve all pantry items (WITH FULL ITEM DETAILS)
    fun getAll(): Flow<List<PantryItemModel>> = pantryItemDao.getAll()

    // 🔹 Retrieve paginated pantry items (WITH FULL ITEM DETAILS)
    fun getAllPaged(): Flow<PagingData<PantryItemModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pantryItemDao.getAllPaged() }
        ).flow

    // 🔹 Update an existing pantry item
    suspend fun update(item: PantryItem): Int = pantryItemDao.update(item)

    // 🔹 Delete a specific pantry item by ID
    suspend fun delete(id: Long): Int = pantryItemDao.delete(id)

    // 🔹 Delete all pantry items (Full reset)
    suspend fun deleteAll(): Int = pantryItemDao.deleteAll()
}
