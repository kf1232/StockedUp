package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fink.stockedup.data.repository.ItemRepository
import com.fink.stockedup.data.repository.PantryItemRepository

class ViewModelFactory(
    private val itemRepository: ItemRepository,
    private val pantryItemRepository: PantryItemRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ItemViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                ItemViewModel(itemRepository) as T
            }
            modelClass.isAssignableFrom(PantryItemViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                PantryItemViewModel(pantryItemRepository) as T
            }
            else -> throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel class - ${modelClass.name}")
        }
    }
}
