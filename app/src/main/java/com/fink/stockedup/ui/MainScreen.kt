package com.fink.stockedup.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.fink.stockedup.ui.theme.StockedUpTheme
import com.fink.stockedup.viewmodel.PantryItemViewModel

@Composable
fun MainScreen(viewModel: PantryItemViewModel) {
    StockedUpTheme {
        Scaffold(
            modifier = Modifier,
            floatingActionButton = {
                FloatingActionButton( onClick = {  null } ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Item")
                }
            }
        ) { innerPadding ->
            Row (modifier = Modifier.padding(innerPadding)) {
                Text("Hello World")
            }
        }
    }
}
