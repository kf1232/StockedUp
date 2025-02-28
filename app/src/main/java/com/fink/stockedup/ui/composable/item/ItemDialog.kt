package com.fink.stockedup.ui.composable.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.Item

@Composable
fun ItemDialog(item: Item?, onDismiss: () -> Unit, onSave: (Item) -> Unit) {
		var itemName by remember { mutableStateOf(item?.itemName ?: "") }
		var itemCategory by remember { mutableStateOf(item?.itemCategory ?: "") }
		var itemUnit by remember { mutableStateOf(item?.itemUnit ?: "") }
		var itemNotes by remember { mutableStateOf(item?.itemNotes ?: "") }
		var itemFavorite by remember { mutableStateOf(item?.itemFavorite ?: false) }

		AlertDialog(
				onDismissRequest = { onDismiss() },
				title = { Text(text = if (item == null) "Add Item" else "Edit Item") },
				text = {
						Column {
								// 🔹 Name Field
								OutlinedTextField(
										value = itemName,
										onValueChange = { itemName = it },
										label = { Text("Item Name") }
								)

								// 🔹 Category Field
								OutlinedTextField(
										value = itemCategory,
										onValueChange = { itemCategory = it },
										label = { Text("Category") }
								)

								// 🔹 Unit Field
								OutlinedTextField(
										value = itemUnit,
										onValueChange = { itemUnit = it },
										label = { Text("Unit (e.g., kg, pack, bottle)") }
								)

								// 🔹 Notes Field
								OutlinedTextField(
										value = itemNotes,
										onValueChange = { itemNotes = it },
										label = { Text("Notes (optional)") }
								)

								// 🔹 Favorite Toggle
								Row(
										verticalAlignment = Alignment.CenterVertically,
										modifier = Modifier.padding(top = 8.dp)
								) {
										Checkbox(
												checked = itemFavorite,
												onCheckedChange = { itemFavorite = it }
										)
										Text(text = "Mark as Favorite")
								}
						}
				},
				confirmButton = {
						TextButton(
								onClick = {
										onSave(
												item?.copy(
														itemName = itemName,
														itemCategory = itemCategory,
														itemUnit = itemUnit,
														itemNotes = itemNotes,
														itemFavorite = itemFavorite,
														itemLastUpdated = System.currentTimeMillis()
												) ?: Item(
														itemName = itemName,
														itemCategory = itemCategory,
														itemUnit = itemUnit,
														itemNotes = itemNotes,
														itemFavorite = itemFavorite,
												)
										)
										onDismiss()
								}
						) { Text("Save") }
				},
				dismissButton = {
						TextButton(onClick = { onDismiss() }) { Text("Cancel") }
				}
		)
}