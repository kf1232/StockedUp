package com.fink.stockedup.data.entity

import androidx.room.*

@Entity(tableName = "recipe_list")
data class RecipeList(
    @PrimaryKey(autoGenerate = true)
    val recipeListId: Long = 0,
    val recipeName: String,
    val recipeDescription: String? = null,
    val recipeUrl: String? = null,
    val recipeCategory: String? = null,
    val recipePreparationTime: Int? = null,
    val recipeCookTime: Int? = null,
    val recipeServings: Int? = null,
    val recipeFavorite: Boolean = false,
    val recipeNotes: String? = null,
    val recipeCreatedAt: Long = System.currentTimeMillis(),
    val recipeUpdatedAt: Long = System.currentTimeMillis()
)

// TODO recipeCategory could be an ENUM field, only so many meal types