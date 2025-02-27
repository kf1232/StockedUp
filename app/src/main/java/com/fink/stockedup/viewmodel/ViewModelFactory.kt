package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fink.stockedup.repository.ItemRepository
import com.fink.stockedup.repository.PantryItemRepository
import com.fink.stockedup.repository.RecipeRepository

class ViewModelFactory(
    private val itemRepository: ItemRepository,
    private val pantryItemRepository: PantryItemRepository,
    private val recipeRepository: RecipeRepository
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
            modelClass.isAssignableFrom(RecipeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                RecipeViewModel(recipeRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
