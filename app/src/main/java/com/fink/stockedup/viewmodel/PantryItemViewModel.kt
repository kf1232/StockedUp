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
    val allPantryItems: StateFlow<List<PantryItemModel>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // 🔹 Flow for observing paged pantry items
    val pagedPantryItems: Flow<PagingData<PantryItemModel>> = repository.getAllPaged()
        .cachedIn(viewModelScope)

    // 🔹 Create a new pantry item
    fun createPantryItem(item: PantryItem) {
        viewModelScope.launch {
            repository.create(item)
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
        }
    }

    // 🔹 Delete a pantry item by ID
    fun deletePantryItem(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    // 🔹 Delete all pantry items (Full Reset)
    fun deleteAllPantryItems() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}
