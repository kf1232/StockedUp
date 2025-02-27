package com.fink.stockedup.data.dao

import androidx.room.*
import com.fink.stockedup.data.entity.RecipeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeItemDao {

    // Insert a new ingredient into a recipe (STRICT CREATE)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(recipeItem: RecipeItem): Long

    // Update a recipe ingredient (returns number of affected rows)
    @Update
    suspend fun update(recipeItem: RecipeItem): Int

    // Delete a specific ingredient from a recipe (returns number of affected rows)
    @Query("DELETE FROM recipe_item WHERE recipeItemId = :id")
    suspend fun delete(id: Long): Int

    // Delete all ingredients for a specific recipe
    @Query("DELETE FROM recipe_item WHERE recipeListId = :recipeId")
    suspend fun deleteAllForRecipe(recipeId: Long): Int
}
