package com.fink.stockedup.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import kotlinx.coroutines.flow.*
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.repository.ItemRepository
import kotlinx.coroutines.launch


class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    // 🔹 Flow for observing all items (Live Updates)
    val allItems: StateFlow<List<Item>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // 🔹 Flow for observing paged items
    val pagedItems: Flow<PagingData<Item>> = repository.getAllPaged()
        .cachedIn(viewModelScope)

    // 🔹 Create a new item
    fun createItem(item: Item) {
        viewModelScope.launch {
            repository.create(item)
        }
    }

    // 🔹 Get a specific item by ID
    fun getItemById(id: Long): Flow<Item?> {
        return repository.get(id)
    }

    // 🔹 Update an existing item
    fun updateItem(item: Item) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    // 🔹 Delete an item by ID
    fun deleteItem(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    // 🔹 Delete all items (Reset)
    fun deleteAllItems() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}
