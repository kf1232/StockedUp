package com.fink.stockedup.ui.composable.pantry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem

@Composable
fun PantryItemRow(
		pantryItem: PantryItem,
		onEdit: (PantryItem) -> Unit,
		onDelete: (PantryItem) -> Unit
) {
		Card(
				modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 4.dp)
						.clickable { onEdit(pantryItem) },
				elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
		) {
				Row(
						modifier = Modifier
								.fillMaxWidth()
								.padding(16.dp),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
				) {
						Column {
								Text(text = pantryItem.pantryItemName ?: "Unnamed", style = MaterialTheme.typography.bodyLarge)
								Text(text = "Quantity: ${pantryItem.pantryItemQuantity}", style = MaterialTheme.typography.bodyMedium)
						}
						IconButton(onClick = { onDelete(pantryItem) }) {
								Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
						}
				}
		}
}
