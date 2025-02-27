package com.fink.stockedup.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.fink.stockedup.data.entity.RecipeList
import com.fink.stockedup.data.model.RecipeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeListDao {

    // 🔹 Insert a new recipe (STRICT CREATE)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(recipe: RecipeList): Long

    // 🔹 Retrieve a single recipe (WITH FULL INGREDIENTS)
    @Transaction
    @Query("SELECT * FROM recipe_list WHERE recipeListId = :id LIMIT 1")
    fun get(id: Long): Flow<RecipeModel?>

    // 🔹 Retrieve all recipes (WITH FULL INGREDIENTS)
    @Transaction
    @Query("SELECT * FROM recipe_list ORDER BY recipeName ASC")
    fun getAll(): Flow<List<RecipeModel>>

    // 🔹 Retrieve paginated recipes (WITH FULL INGREDIENTS)
    @Transaction
    @Query("SELECT * FROM recipe_list ORDER BY recipeName ASC")
    fun getAllPaged(): PagingSource<Int, RecipeModel>

    // 🔹 Retrieve only `RecipeList` without ingredients
    @Query("SELECT * FROM recipe_list WHERE recipeListId = :id LIMIT 1")
    fun getRecipeListOnly(id: Long): Flow<RecipeList?>

    // 🔹 Retrieve all recipe headers (WITHOUT INGREDIENTS)
    @Query("SELECT * FROM recipe_list ORDER BY recipeName ASC")
    fun getAllRecipeHeaders(): Flow<List<RecipeList>>

    // 🔹 Retrieve paginated recipe headers (WITHOUT INGREDIENTS)
    @Query("SELECT * FROM recipe_list ORDER BY recipeName ASC")
    fun getAllPagedRecipeHeaders(): PagingSource<Int, RecipeList>

    // 🔹 Update an existing recipe (returns number of affected rows)
    @Update
    suspend fun update(recipe: RecipeList): Int

    // 🔹 Delete a recipe by ID (Cascade deletes RecipeItems)
    @Query("DELETE FROM recipe_list WHERE recipeListId = :id")
    suspend fun delete(id: Long): Int

    // 🔹 Delete all recipes (Cascade deletes all RecipeItems)
    @Query("DELETE FROM recipe_list")
    suspend fun deleteAll(): Int
}
