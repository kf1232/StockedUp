package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.data.repository.ItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ItemViewModel constructor(
		private val repository: ItemRepository
) : ViewModel() {

		// [ R ] READ - Live updates of all items
		private val _allItems = MutableStateFlow<List<Item>>(emptyList())
		val allItems: StateFlow<List<Item>> = _allItems.asStateFlow()

		// [ R ] READ - Fetch items from the repository
		init {
				viewModelScope.launch {
						repository.getAllItems().collect { items ->
								_allItems.value = items
						}
				}
		}

		// [ C ] CREATE - Add a new item
		fun addItem(item: Item) {
				viewModelScope.launch {
						repository.addItem(item)
				}
		}

		// [ R ] READ - Get item by ID
		private val _selectedItem = MutableStateFlow<Item?>(null)
		val selectedItem: StateFlow<Item?> = _selectedItem.asStateFlow()

		fun getItemById(id: Long) {
				viewModelScope.launch {
						_selectedItem.value = repository.getItemById(id)
				}
		}

		// [ R ] READ - Get paginated items
		fun getPagedItems(): PagingSource<Int, Item> {
				return repository.getPagedItems()
		}

		// [ R ] READ - Get items by category
		private val _itemsByCategory = MutableStateFlow<List<Item>>(emptyList())
		val itemsByCategory: StateFlow<List<Item>> = _itemsByCategory.asStateFlow()

		fun getAllCategory(category: String) {
				viewModelScope.launch {
						repository.getAllCategory(category).collect { items ->
								_itemsByCategory.value = items
						}
				}
		}

		// [ R ] READ - Get favorite items
		private val _favoriteItems = MutableStateFlow<List<Item>>(emptyList())
		val favoriteItems: StateFlow<List<Item>> = _favoriteItems.asStateFlow()

		fun getAllFavorites() {
				viewModelScope.launch {
						repository.getAllFavorites().collect { items ->
								_favoriteItems.value = items
						}
				}
		}

		// [ U ] UPDATE - Update an item
		fun updateItem(item: Item) {
				viewModelScope.launch {
						repository.updateItem(item)
				}
		}

		// [ D ] DELETE - Delete an item by ID
		fun deleteItemById(id: Long) {
				viewModelScope.launch {
						repository.deleteItemById(id)
				}
		}

		// [ D ] DELETE - Delete all items
		fun deleteAll() {
				viewModelScope.launch {
						repository.deleteAll()
				}
		}
}
