package com.fink.stockedup.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.data.entity.RecipeItem

@Entity
data class RecipeItemDetail (
    @Embedded val recipeItem: RecipeItem,

    @Relation(
        parentColumn = "itemId",
        entityColumn = "itemId"
    )
    val item: Item
)