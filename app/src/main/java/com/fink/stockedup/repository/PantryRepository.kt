package com.fink.stockedup.repository

import com.fink.stockedup.data.dao.PantryDao
import com.fink.stockedup.data.entity.PantryItem
import kotlinx.coroutines.flow.Flow

class PantryRepository(private val pantryDao: PantryDao) {

    // 🔹 Get all pantry items as a Flow (for real-time updates)
    val allItems: Flow<List<PantryItem>> = pantryDao.getAllItems()

    // 🔹 Insert a new item into the database
    suspend fun addItem(item: PantryItem): Long {
        return pantryDao.addItem(item)
    }

    // 🔹 Update an existing pantry item
    suspend fun updateItem(item: PantryItem) {
        pantryDao.updateItem(item)
    }

    // 🔹 Delete a specific item
    suspend fun deleteItem(item: PantryItem) {
        pantryDao.deleteItem(item)
    }

    // 🔹 Get a specific item by ID
    suspend fun getItemById(id: Long): PantryItem? {
        return pantryDao.getItemById(id)
    }
}
