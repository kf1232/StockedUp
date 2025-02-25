package com.fink.stockedup.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem
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

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(pantryItems) { item ->
                    PantryItemRow(item = item, onDelete = { viewModel.deleteItem(it) })
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

@Composable
fun PantryItemRow(item: PantryItem, onDelete: (PantryItem) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = item.item_name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { onDelete(item) }) {
                Text("Delete")
            }
        }
    }
}
