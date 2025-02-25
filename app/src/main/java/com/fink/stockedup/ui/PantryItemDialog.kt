package com.fink.stockedup.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem
import java.time.LocalDate

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PantryItemDialog(onDismiss: () -> Unit, onConfirm: (PantryItem) -> Unit) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("1") }
    var itemCategory by remember { mutableStateOf("") }
    var itemExpirationDate by remember { mutableStateOf("") }
    var itemStorageLocation by remember { mutableStateOf("") }
    var itemUnit by remember { mutableStateOf("") }
    var itemNotes by remember { mutableStateOf("") }
    var itemFavorite by remember { mutableStateOf(false) }


    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (itemName.isNotEmpty() && itemQuantity.toIntOrNull() != null) {
                    val parsedDate = try {
                        dateFormat.parse(itemExpirationDate)
                    } catch (e: Exception) {
                        null
                    }

                    val newItem = PantryItem(
                        item_name = itemName,
                        item_quantity = itemQuantity.toInt(),
                        item_category = itemCategory.ifEmpty { "Uncategorized" },
                        item_expiration_date = parsedDate,
                        item_last_updated = System.currentTimeMillis(),
                        item_id = 0,
                        item_storage_location = itemStorageLocation.ifEmpty { "Unknown" },
                        item_unit = itemUnit.ifEmpty { "Units" },
                        item_notes = itemNotes.ifEmpty { "" },
                        item_favorite = itemFavorite
                    )
                    onConfirm(newItem)
                }
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Pantry Item") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp) // ✅ Manages spacing automatically
            ) {
                TextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Item Name") }
                )

                TextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    label = { Text("Quantity") }
                )

                TextField(
                    value = itemCategory,
                    onValueChange = { itemCategory = it },
                    label = { Text("Category") }
                )

                TextField(
                    value = itemExpirationDate,
                    onValueChange = { itemExpirationDate = it },
                    label = { Text("Expiration Date (YYYY-MM-DD)") }
                )

                TextField(
                    value = itemStorageLocation,
                    onValueChange = { itemStorageLocation = it },
                    label = { Text("Storage Location") }
                )

                TextField(
                    value = itemUnit,
                    onValueChange = { itemUnit = it },
                    label = { Text("Unit") }
                )

                TextField(
                    value = itemNotes,
                    onValueChange = { itemNotes = it },
                    label = { Text("Notes") }
                )

                // ✅ Favorite Toggle Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Favorite")
                    Switch(
                        checked = itemFavorite,
                        onCheckedChange = { itemFavorite = it }
                    )
                }
            }

        }
    )
}

