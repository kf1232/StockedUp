package com.fink.stockedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.fink.stockedup.data.database.StockedUpDatabase
import com.fink.stockedup.data.repository.ItemRepository
import com.fink.stockedup.data.repository.PantryItemRepository
import com.fink.stockedup.ui.composable.MainScreen
import com.fink.stockedup.viewmodel.ItemViewModel
import com.fink.stockedup.viewmodel.PantryItemViewModel
import com.fink.stockedup.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    // Initialize database instance
    private val database: StockedUpDatabase by lazy { StockedUpDatabase.getDatabase(this) }

    // Initialize repositories
    private val itemRepository: ItemRepository by lazy { ItemRepository(database.itemDao()) }
    private val pantryItemRepository: PantryItemRepository by lazy { PantryItemRepository(database.pantryItemDao(), database.itemDao()) }

    // Initialize ViewModelFactory
    private val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(itemRepository, pantryItemRepository)
    }

    // Initialize ViewModels
    private val itemViewModel: ItemViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ItemViewModel::class.java]
    }

    private val pantryItemViewModel: PantryItemViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PantryItemViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                itemViewModel = itemViewModel,
                pantryItemViewModel = pantryItemViewModel
            )
        }
    }
}



