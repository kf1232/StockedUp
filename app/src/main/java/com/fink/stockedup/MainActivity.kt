package com.fink.stockedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.fink.stockedup.data.database.PantryDatabase
import com.fink.stockedup.repository.ItemRepository
import com.fink.stockedup.repository.PantryItemRepository
import com.fink.stockedup.repository.RecipeRepository
import com.fink.stockedup.ui.MainScreen
import com.fink.stockedup.viewmodel.PantryItemViewModel
import com.fink.stockedup.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var pantryViewModel: PantryItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Initialize Database & Repositories
        val database = PantryDatabase.getDatabase(this)
        val itemRepository = ItemRepository(database.itemDao())
        val pantryRepository = PantryItemRepository(database.pantryItemDao())
        val recipeRepository = RecipeRepository(
            recipeItemDao = database.recipeItemDao(),
            recipeListDao = database.recipeListDao()
        )

        // ✅ Initialize ViewModelFactory
        val factory = ViewModelFactory(
            itemRepository = itemRepository,
            pantryItemRepository = pantryRepository,
            recipeRepository = recipeRepository
        )

        // ✅ Correct ViewModel Instantiation
        pantryViewModel = ViewModelProvider(this, factory)[PantryItemViewModel::class.java]

        setContent {
            MainScreen()
        }
    }
}
