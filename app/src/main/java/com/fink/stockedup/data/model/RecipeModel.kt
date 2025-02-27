package com.fink.stockedup.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.fink.stockedup.data.entity.RecipeList

data class RecipeModel (
    @Embedded val recipe: RecipeList,

    @Relation(
        parentColumn = "recipeListId",
        entityColumn = "recipeListId"
    )
    val ingredients: List<RecipeItemDetail>
)
