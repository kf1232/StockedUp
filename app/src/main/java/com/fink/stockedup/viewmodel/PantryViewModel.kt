package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.repository.PantryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PantryViewModel(private val repository: PantryRepository) : ViewModel() {

    // 🔹 Expose pantry items as a StateFlow (UI can observe this)
    private val _pantryItems = MutableStateFlow<List<PantryItem>>(emptyList())
    val pantryItems: StateFlow<List<PantryItem>> = _pantryItems

    init {
        fetchPantryItems()
    }

    // 🔹 Fetch items from the repository
    private fun fetchPantryItems() {
        viewModelScope.launch {
            repository.allItems.collect { items ->
                _pantryItems.value = items
            }
        }
    }

    // 🔹 Add a new item
    fun addItem(item: PantryItem) {
        viewModelScope.launch {
            repository.addItem(item)
        }
    }

    // 🔹 Update an existing item
    fun updateItem(item: PantryItem) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    // 🔹 Delete an item
    fun deleteItem(item: PantryItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}
