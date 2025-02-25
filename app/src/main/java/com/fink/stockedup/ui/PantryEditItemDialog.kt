package com.fink.stockedup.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.utils.DateUtils.formatDate
import java.time.Instant
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PantryEditItemDialog(
    existingItem: PantryItem,
    onDismiss: () -> Unit,
    onConfirm: (PantryItem) -> Unit
) {
    var itemName by remember { mutableStateOf(existingItem.itemName) }
    var itemQuantity by remember { mutableStateOf(existingItem.itemQuantity.toString()) }
    var itemCategory by remember { mutableStateOf(existingItem.itemCategory) }
    var itemExpirationDate by remember { mutableStateOf(existingItem.itemExpirationDate?.let { formatDate(it) } ?: "") }
    var itemStorageLocation by remember { mutableStateOf(existingItem.itemStorageLocation) }
    var itemUnit by remember { mutableStateOf(existingItem.itemUnit) }
    var itemNotes by remember { mutableStateOf(existingItem.itemNotes) }
    var itemFavorite by remember { mutableStateOf(existingItem.itemFavorite) }

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

                    val updatedItem = existingItem.copy(
                        itemName = itemName,
                        itemQuantity = itemQuantity.toInt(),
                        itemCategory = itemCategory.ifEmpty { "Uncategorized" },
                        itemExpirationDate = parsedDate,
                        itemStorageLocation = itemStorageLocation,
                        itemUnit = itemUnit,
                        itemNotes = itemNotes,
                        itemFavorite = itemFavorite,
                        itemLastUpdated = Instant.now().toEpochMilli()
                    )
                    onConfirm(updatedItem)
                }
            }) {
                Text("Update")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Edit Pantry Item") },
        text = {
            Column {
                TextField(value = itemName, onValueChange = { itemName = it }, label = { Text("Item Name") })
                TextField(value = itemQuantity, onValueChange = { itemQuantity = it }, label = { Text("Quantity") })
                TextField(value = itemCategory, onValueChange = { itemCategory = it }, label = { Text("Category") })
                TextField(value = itemExpirationDate, onValueChange = { itemExpirationDate = it }, label = { Text("Expiration Date (YYYY-MM-DD)") })
                TextField(value = itemStorageLocation, onValueChange = { itemStorageLocation = it }, label = { Text("Storage Location") })
                TextField(value = itemUnit, onValueChange = { itemUnit = it }, label = { Text("Unit") })
                TextField(value = itemNotes ?: "", onValueChange = { itemNotes = it }, label = { Text("Notes") })

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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
