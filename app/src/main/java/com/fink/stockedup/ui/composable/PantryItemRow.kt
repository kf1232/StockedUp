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
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import com.fink.stockedup.utils.DateUtils.formatDate
import com.fink.stockedup.utils.DateUtils.formatDateTime
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Date

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
                    onTap = { isExpanded = !isExpanded } // ✅ Prevents conflict between tap & long press
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // ✅ Normal State Header (Always Visible)
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

                // ✅ Show Quantity Buttons Only When Not Expanded
                if (!isExpanded) {
                    QuantityButtonsRow(item, onUpdate)
                }
            }

            // ✅ Expanded Content (Smooth Transition)
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(400)),
                exit = fadeOut(animationSpec = tween(200)) + shrinkVertically(animationSpec = tween(300))
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text("Quantity: ${item.itemQuantity}", fontSize = 14.sp)
                    Text("Category: ${item.itemCategory}", fontSize = 14.sp)
                    Text(
                        text = "Expiration: ${item.itemExpirationDate?.let { formatDate(it) } ?: "N/A"}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Days till Spoiled: ${item.itemExpirationDate?.let { calculateDaysUntilExpiration(it) } ?: "N/A"}",
                        fontSize = 14.sp
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

fun calculateDaysUntilExpiration(expirationDate: Date): Long {
    val today = LocalDate.now()
    val expiration = Instant.ofEpochMilli(expirationDate.time)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    return ChronoUnit.DAYS.between(today, expiration)
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
                        itemLastUpdated = Instant.now().toEpochMilli()
                    )
                    onUpdate(updatedItem)
                }
            }
        )

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
}
