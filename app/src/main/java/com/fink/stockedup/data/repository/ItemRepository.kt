package com.fink.stockedup.data.repository

import androidx.paging.PagingSource
import com.fink.stockedup.data.dao.ItemDao
import com.fink.stockedup.data.entity.Item
import kotlinx.coroutines.flow.Flow

class ItemRepository constructor(
    private val pantryDao: ItemDao
) {
    // [ C ] CREATE
    // 🔹 Insert a new item (Fails if it already exists)
    suspend fun addItem(item: Item): Long {
        return pantryDao.addItem(item)
    }

    // [ R ] READ
    // 🔹 Get all pantry items as a Flow (for real-time updates)
    fun getAllItems(): Flow<List<Item>> {
        return pantryDao.getAllItems()
    }

    // 🔹 Get paged items (for UI pagination)
    fun getPagedItems(): PagingSource<Int, Item> {
        return pantryDao.getPagedItems()
    }

    // 🔹 Get a specific item by ID
    suspend fun getItemById(id: Long): Item? {
        return pantryDao.getItemById(id)
    }

    // 🔹 Get all items in a specific category
    fun getAllCategory(category: String): Flow<List<Item>> {
        return pantryDao.getAllCategory(category)
    }

    // 🔹 Get all favorite items
    fun getAllFavorites(): Flow<List<Item>> {
        return pantryDao.getAllFavorites()
    }

    // [ U ] UPDATE
    // 🔹 Update an existing pantry item
    suspend fun updateItem(item: Item) {
        pantryDao.updateItem(item)
    }

    // [ D ] DELETE
    // 🔹 Delete a specific item by ID
    suspend fun deleteItemById(id: Long) {
        pantryDao.deleteItemById(id)
    }

    // 🔹 Delete all items from the pantry
    suspend fun deleteAll() {
        pantryDao.deleteAll()
    }
}
