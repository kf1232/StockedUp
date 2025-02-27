package com.fink.stockedup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fink.stockedup.data.model.RecipeModel
import com.fink.stockedup.data.entity.RecipeItem
import com.fink.stockedup.data.entity.RecipeList
import com.fink.stockedup.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    // 🔹 Flow for observing all recipes (Live Updates)
    val allRecipes: StateFlow<List<RecipeModel>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // 🔹 Flow for observing paged recipes
    val pagedRecipes: Flow<PagingData<RecipeModel>> = repository.getAllPaged()
        .cachedIn(viewModelScope)

    // 🔹 Create a new recipe
    fun createRecipe(recipe: RecipeList) {
        viewModelScope.launch {
            repository.create(recipe)
        }
    }

    // 🔹 Get a specific recipe by ID (WITH INGREDIENTS)
    fun getRecipeById(id: Long): Flow<RecipeModel?> {
        return repository.get(id)
    }

    // 🔹 Update an existing recipe
    fun updateRecipe(recipe: RecipeList) {
        viewModelScope.launch {
            repository.update(recipe)
        }
    }

    // 🔹 Delete a recipe by ID
    fun deleteRecipe(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    // 🔹 Delete all recipes (Full Reset)
    fun deleteAllRecipes() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    // --- Recipe Ingredients Management ---

    // 🔹 Add an ingredient to an existing recipe
    fun addIngredientToRecipe(recipeItem: RecipeItem) {
        viewModelScope.launch {
            repository.addIngredient(recipeItem)
        }
    }

    // 🔹 Update a specific ingredient in a recipe
    fun updateIngredientInRecipe(recipeItem: RecipeItem) {
        viewModelScope.launch {
            repository.updateIngredient(recipeItem)
        }
    }

    // 🔹 Remove a specific ingredient from a recipe
    fun removeIngredientFromRecipe(id: Long) {
        viewModelScope.launch {
            repository.deleteIngredient(id)
        }
    }

    // 🔹 Remove all ingredients from a specific recipe
    fun clearIngredientsFromRecipe(recipeId: Long) {
        viewModelScope.launch {
            repository.deleteAllIngredientsForRecipe(recipeId)
        }
    }
}
