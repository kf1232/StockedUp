package com.fink.stockedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.fink.stockedup.data.database.PantryDatabase
import com.fink.stockedup.repository.PantryRepository
import com.fink.stockedup.ui.PantryScreen
import com.fink.stockedup.ui.theme.StockedUpTheme
import com.fink.stockedup.viewmodel.PantryViewModel
import com.fink.stockedup.viewmodel.PantryViewModelFactory

class MainActivity : ComponentActivity() {
    private val database: PantryDatabase by lazy { PantryDatabase.getDatabase(this) }
    private val repository: PantryRepository by lazy { PantryRepository(database.pantryDao()) }

    private val pantryViewModel: PantryViewModel by lazy {
        ViewModelProvider(this, PantryViewModelFactory(repository))[PantryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockedUpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantryScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = pantryViewModel
                    )
                }
            }
        }
    }
}
