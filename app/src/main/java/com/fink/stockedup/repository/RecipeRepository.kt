package com.fink.stockedup.repository

import androidx.paging.*
import com.fink.stockedup.data.dao.RecipeListDao
import com.fink.stockedup.data.dao.RecipeItemDao
import com.fink.stockedup.data.entity.RecipeItem
import com.fink.stockedup.data.entity.RecipeList
import com.fink.stockedup.data.model.RecipeModel
import kotlinx.coroutines.flow.Flow

class RecipeRepository(
    private val recipeListDao: RecipeListDao,
    private val recipeItemDao: RecipeItemDao
) {

    // 🔹 Create a new recipe (STRICT CREATE)
    suspend fun create(recipe: RecipeList): Long = recipeListDao.create(recipe)

    // 🔹 Retrieve a single recipe with ingredients
    fun get(id: Long): Flow<RecipeModel?> = recipeListDao.get(id)

    // 🔹 Retrieve all recipes with ingredients (Live tracking)
    fun getAll(): Flow<List<RecipeModel>> = recipeListDao.getAll()

    // 🔹 Retrieve paginated recipes with ingredients (FIX: Converts PagingSource → Flow<PagingData<RecipeModel>>)
    fun getAllPaged(): Flow<PagingData<RecipeModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { recipeListDao.getAllPaged() }
        ).flow

    // 🔹 Update an existing recipe
    suspend fun update(recipe: RecipeList): Int = recipeListDao.update(recipe)

    // 🔹 Delete a specific recipe by ID (Cascade deletes RecipeItems)
    suspend fun delete(id: Long): Int = recipeListDao.delete(id)

    // 🔹 Delete all recipes (Cascade deletes all RecipeItems)
    suspend fun deleteAll(): Int = recipeListDao.deleteAll()

    // --- Recipe Item Management ---

    // 🔹 Add an ingredient to an existing recipe
    suspend fun addIngredient(recipeItem: RecipeItem): Long = recipeItemDao.create(recipeItem)

    // 🔹 Update a specific ingredient in a recipe
    suspend fun updateIngredient(recipeItem: RecipeItem): Int = recipeItemDao.update(recipeItem)

    // 🔹 Remove a specific ingredient from a recipe
    suspend fun deleteIngredient(id: Long): Int = recipeItemDao.delete(id)

    // 🔹 Remove all ingredients from a specific recipe
    suspend fun deleteAllIngredientsForRecipe(recipeId: Long): Int = recipeItemDao.deleteAllForRecipe(recipeId)
}
