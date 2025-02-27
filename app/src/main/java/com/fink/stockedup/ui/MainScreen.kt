package com.fink.stockedup.ui

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.fink.stockedup.ui.composable.pantry.PantryScreen
import com.fink.stockedup.ui.theme.StockedUpTheme

@Composable
fun MainScreen( viewModel: PantryViewModel ) {
    StockedUpTheme {
        PantryScreen(
            modifier = Modifier,
            viewModel = viewModel
        )
    }
}
