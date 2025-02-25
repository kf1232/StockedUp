package com.fink.stockedup.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.ui.composable.PantryItemRow
import com.fink.stockedup.viewmodel.PantryViewModel

@Composable
fun PantryScreen(viewModel: PantryViewModel) {
    var showDialog by remember { mutableStateOf(false) } // ✅ State to show input dialog
    val pantryItems = viewModel.pantryItems.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) { // ✅ Opens dialog
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Pantry Items", style = MaterialTheme.typography.headlineMedium)

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(pantryItems) { item ->
                    PantryItemRow(
                        modifier = Modifier,
                        item = item,
                        onDelete = { viewModel.deleteItem(it) },
                        onUpdate = { updatedItem -> viewModel.updateItem(updatedItem) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        PantryItemDialog(
            onDismiss = { showDialog = false },
            onConfirm = { newItem ->
                viewModel.addItem(newItem) // ✅ Now adds the full item data
                showDialog = false
            }
        )
    }

}
