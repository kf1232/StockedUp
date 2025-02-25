package com.fink.stockedup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
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
    private lateinit var pantryViewModel: PantryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Create Database & Repository
        val database = PantryDatabase.getDatabase(this)
        val repository = PantryRepository(database.pantryDao())

        // ✅ Manually initialize ViewModel with Factory
        pantryViewModel = ViewModelProvider(
            this,
            PantryViewModelFactory(repository)
        )[PantryViewModel::class.java]
        setContent {
            StockedUpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        PantryScreen(pantryViewModel)
                    }
                }
            }
        }
    }
}
