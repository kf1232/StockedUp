package com.fink.stockedup.data.entity

import androidx.room.*

@Entity(
    tableName = "recipe_item",
    foreignKeys = [
        ForeignKey(
            entity = RecipeList::class,
            parentColumns = ["recipeListId"],
            childColumns = ["recipeListId"],
            onDelete = ForeignKey.CASCADE // Remove ingredients if recipe is deleted
        ),
        ForeignKey(
            entity = Item::class,
            parentColumns = ["itemId"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.RESTRICT // Prevent deleting items still referenced in recipes
        )
    ],
    indices = [
        Index(value = ["recipeListId"]),
        Index(value = ["itemId"])
    ]
)

data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    val recipeItemId: Long = 0,
    val recipeListId: Long,   // FK to RecipeList
    val itemId: Long,   // FK to Item (not PantryItem)
    val recipeItemQuantity: Double,   // Amount required
    val recipeItemUnit: String?   // Measurement unit (e.g., "tbsp", "kg")
)


// TODO standardize the unit method, this should match the TODO in PantryItem
// TODO optional items could be added to this in the future
// TODO substitution items could be added to this in the future.


// TODO define the workflow for what happens in the event of x or y happening to the FK data.