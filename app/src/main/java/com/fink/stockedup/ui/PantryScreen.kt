package com.fink.stockedup.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.viewmodel.PantryViewModel

@Composable
fun PantryScreen(viewModel: PantryViewModel) {
    val pantryItems = viewModel.pantryItems.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Pantry Items", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pantryItems) { item ->
                PantryItemRow(item = item, onDelete = { viewModel.deleteItem(it) })
            }
        }
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
