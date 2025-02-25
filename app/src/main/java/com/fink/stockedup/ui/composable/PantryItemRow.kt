package com.fink.stockedup.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem
import java.time.Instant
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip

@Composable
fun PantryItemRow(
    modifier: Modifier = Modifier,
    item: PantryItem,
    onUpdate: (PantryItem) -> Unit,
    onDelete: (PantryItem) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { isExpanded = !isExpanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.itemName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // ✅ Decrease Button
                DecreaseCountButton(
                    itemQuantity = item.itemQuantity,
                    onClick = {
                        if (item.itemQuantity > 0) {
                            val updatedItem = item.copy(
                                itemQuantity = item.itemQuantity - 1,
                                itemLastUpdated = Instant.now().toEpochMilli()
                            )
                            onUpdate(updatedItem)
                        }
                    }
                )

                // ✅ Increase Button
                IncreaseCountButton(
                    onClick = {
                        val updatedItem = item.copy(
                            itemQuantity = item.itemQuantity + 1,
                            itemLastUpdated = Instant.now().toEpochMilli()
                        )
                        onUpdate(updatedItem)
                    }
                )


            }

            // ✅ Expanded State (Only when clicked)
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text("Quantity: ${item.itemQuantity}", fontSize = 14.sp)
                    Text("Category: ${item.itemCategory}", fontSize = 14.sp)
                    Text("Expiration: ${item.itemExpirationDate ?: "N/A"}", fontSize = 14.sp)
                    Text("Location: ${item.itemStorageLocation}", fontSize = 14.sp)
                    Text("Unit: ${item.itemUnit}", fontSize = 14.sp)
                    Text("Notes: ${item.itemNotes}", fontSize = 14.sp)
                    Text("Last Updated: ${Instant.ofEpochMilli(item.itemLastUpdated)}", fontSize = 14.sp)
                    Text("Favorite: ${if (item.itemFavorite) "Yes" else "No"}", fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    // ✅ Bottom Row for Actions (Delete, Increase, Decrease)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ActionButton("Delete", Color.Red) { onDelete(item) }
                        ActionButton("-", Color.Yellow, enabled = item.itemQuantity > 0) {
                            if (item.itemQuantity > 0) {
                                val updatedItem = item.copy(
                                    itemQuantity = item.itemQuantity - 1,
                                    itemLastUpdated = Instant.now().toEpochMilli()
                                )
                                onUpdate(updatedItem)
                            }
                        }
                        ActionButton("+", Color.Green) {
                            val updatedItem = item.copy(
                                itemQuantity = item.itemQuantity + 1,
                                itemLastUpdated = Instant.now().toEpochMilli()
                            )
                            onUpdate(updatedItem)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuantityButton(text: String, enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(36.dp) // ✅ Small, modern buttons
            .clip(RoundedCornerShape(50)),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ActionButton(text: String, color: Color, enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 6.dp)
            .clip(RoundedCornerShape(50)),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}
