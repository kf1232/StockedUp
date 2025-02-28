package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.data.repository.PantryItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PantryItemViewModel(private val repository: PantryItemRepository) : ViewModel() {

		// Live list of all pantry items
		private val _allPantryItems = MutableStateFlow<List<PantryItem>>(emptyList())
		val allPantryItems: StateFlow<List<PantryItem>> = _allPantryItems.asStateFlow()

		// Initialize and observe live pantry items
		init {
				viewModelScope.launch {
						repository.getAllPantryItems().collect { items ->
								_allPantryItems.value = items
						}
				}
		}

		// Add a new pantry item
		fun addPantryItem(pantryItem: PantryItem) {
				viewModelScope.launch {
						repository.addPantryItem(pantryItem)
				}
		}

		// Get pantry items by itemId
		fun getPantryItemsByItemId(itemId: Long): Flow<List<PantryItem>> {
				return repository.getPantryItemsByItemId(itemId)
		}

		// Update an existing pantry item
		fun updatePantryItem(pantryItem: PantryItem) {
				viewModelScope.launch {
						repository.updatePantryItem(pantryItem)
				}
		}

		// Delete a specific pantry item by ID
		fun deletePantryItemById(id: Long) {
				viewModelScope.launch {
						repository.deletePantryItemById(id)
				}
		}

		// Delete all pantry items (DOES NOT delete items)
		fun deleteAllPantryItems() {
				viewModelScope.launch {
						repository.deleteAllPantryItems()
				}
		}
}
