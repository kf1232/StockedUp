package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fink.stockedup.data.model.PantryItemModel
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.repository.PantryItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PantryItemViewModel(private val repository: PantryItemRepository) : ViewModel() {

    // 🔹 Flow for observing all pantry items (Live Updates)
    private val _allPantryItems = MutableStateFlow<List<PantryItemModel>>(emptyList())
    val allPantryItems: StateFlow<List<PantryItemModel>> = _allPantryItems.asStateFlow()

    // 🔹 Flow for observing paged pantry items
    val pagedPantryItems: Flow<PagingData<PantryItemModel>> = repository.getAllPaged()
        .cachedIn(viewModelScope)

    init {
        refreshPantryItems()
    }

    // 🔹 Fetch all pantry items and update _allPantryItems
    private fun refreshPantryItems() {
        viewModelScope.launch {
            repository.getAll().collect { items ->
                _allPantryItems.value = items
            }
        }
    }

    // 🔹 Create a new pantry item
    fun createPantryItem(item: PantryItem) {
        viewModelScope.launch {
            repository.create(item)
            refreshPantryItems() // Ensure UI updates after insert
        }
    }

    // 🔹 Get a specific pantry item by ID
    fun getPantryItemById(id: Long): Flow<PantryItemModel?> {
        return repository.get(id)
    }

    // 🔹 Update an existing pantry item
    fun updatePantryItem(item: PantryItem) {
        viewModelScope.launch {
            repository.update(item)
            refreshPantryItems() // Ensure UI updates after update
        }
    }

    // 🔹 Delete a pantry item by ID
    fun deletePantryItem(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            refreshPantryItems() // Ensure UI updates after deletion
        }
    }

    // 🔹 Delete all pantry items (Full Reset)
    fun deleteAllPantryItems() {
        viewModelScope.launch {
            repository.deleteAll()
            refreshPantryItems() // Ensure UI updates after reset
        }
    }
}
