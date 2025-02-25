package com.fink.stockedup.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.utils.DateUtils.formatDate
import com.fink.stockedup.utils.DateUtils.formatDateTime
import com.fink.stockedup.utils.DateUtils.calculateDaysUntilExpiration

@Composable
fun PantryItemRow(
    modifier: Modifier = Modifier,
    item: PantryItem,
    onUpdate: (PantryItem) -> Unit,
    onDelete: (PantryItem) -> Unit,
    onLongPress: () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongPress() },
                    onTap = { isExpanded = !isExpanded }
                )
            },
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

                if (!isExpanded) {
                    QuantityButtonsRow(item, onUpdate)
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(400)),
                exit = fadeOut(animationSpec = tween(200)) + shrinkVertically(animationSpec = tween(300))
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text("Quantity: ${item.itemQuantity}", fontSize = 14.sp)
                    Text("Category: ${item.itemCategory}", fontSize = 14.sp)

                    val daysLeft = item.itemExpirationDate?.let { calculateDaysUntilExpiration(it) }
                    val expirationColor = when {
                        daysLeft == null -> Color.Gray
                        daysLeft < 0 -> Color.Red
                        daysLeft <= 3 -> Color.Yellow
                        else -> Color.White
                    }

                    Text(
                        text = "Expiration: ${item.itemExpirationDate?.let { formatDate(it) } ?: "N/A"}",
                        fontSize = 14.sp,
                        color = expirationColor
                    )
                    Text(
                        text = "Days till Spoiled: ${daysLeft ?: "N/A"}",
                        fontSize = 14.sp,
                        color = expirationColor
                    )

                    Text("Location: ${item.itemStorageLocation}", fontSize = 14.sp)
                    Text("Unit: ${item.itemUnit}", fontSize = 14.sp)
                    Text("Notes: ${item.itemNotes}", fontSize = 14.sp)
                    Text("Last Updated: ${formatDateTime(item.itemLastUpdated)}", fontSize = 14.sp)
                    Text("Favorite: ${if (item.itemFavorite) "Yes" else "No"}", fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    // ✅ Buttons for Expanded View (Increase, Decrease, Delete)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        QuantityButtonsRow(item, onUpdate)
                        ActionButton("Delete", Color.Red) { onDelete(item) }
                    }
                }
            }
        }
    }
}

// ✅ Extracted Function to Avoid Code Duplication
@Composable
private fun QuantityButtonsRow(
    item: PantryItem,
    onUpdate: (PantryItem) -> Unit
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DecreaseCountButton(
            itemQuantity = item.itemQuantity,
            onClick = {
                if (item.itemQuantity > 0) {
                    val updatedItem = item.copy(
                        itemQuantity = item.itemQuantity - 1,
                        itemLastUpdated = System.currentTimeMillis()
                    )
                    onUpdate(updatedItem)
                }
            }
        )

        IncreaseCountButton(
            onClick = {
                val updatedItem = item.copy(
                    itemQuantity = item.itemQuantity + 1,
                    itemLastUpdated = System.currentTimeMillis()
                )
                onUpdate(updatedItem)
            }
        )
    }
}
