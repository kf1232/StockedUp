package com.fink.stockedup.data.repository

import com.fink.stockedup.data.dao.ItemDao
import com.fink.stockedup.data.dao.PantryItemDao
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.data.entity.PantryItem
import kotlinx.coroutines.flow.Flow

class PantryItemRepository(
		private val pantryItemDao: PantryItemDao,
		private val itemDao: ItemDao
) {

		// [ C ] CREATE - Add a new PantryItem (Creates Item if needed)
		suspend fun addPantryItem(pantryItem: PantryItem): Long {
				return if (itemDao.getItemById(pantryItem.itemId) != null) {
						pantryItemDao.addPantryItem(pantryItem)
				} else {
						val newItemId = itemDao.addItem(
								Item(
										itemName = pantryItem.pantryItemName ?: "New Item",
										itemCategory = pantryItem.pantryItemCategory ?: "Uncategorized",
										itemUnit = pantryItem.pantryItemUnit ?: "Unit"
								)
						)
						pantryItemDao.addPantryItem(pantryItem.copy(itemId = newItemId))
				}
		}

		// [ R ] READ - Get all pantry items (Live Updates)
		fun getAllPantryItems(): Flow<List<PantryItem>> {
				return pantryItemDao.getAllPantryItems()
		}

		// Retrieve a specific PantryItem by ID
		suspend fun getPantryItemById(id: Long): PantryItem? {
				return pantryItemDao.getPantryItemById(id)
		}

		// Retrieve all PantryItems linked to a specific `itemId`
		fun getPantryItemsByItemId(itemId: Long): Flow<List<PantryItem>> {
				return pantryItemDao.getPantryItemsByItemId(itemId)
		}

		// [ U ] UPDATE - Modify an existing PantryItem
		suspend fun updatePantryItem(pantryItem: PantryItem) {
				pantryItemDao.updatePantryItem(pantryItem)
		}

		// [ D ] DELETE - Remove a PantryItem by ID (Does NOT delete from `items`)
		suspend fun deletePantryItemById(id: Long) {
				pantryItemDao.deletePantryItemById(id)
		}

		// Delete all PantryItems (Does NOT delete `items`)
		suspend fun deleteAllPantryItems() {
				pantryItemDao.deleteAllPantryItems()
		}
}
