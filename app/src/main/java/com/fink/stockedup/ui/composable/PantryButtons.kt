package com.fink.stockedup.ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IncreaseCountButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(42.dp) // ✅ Consistent size
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "Increase Count"
        )
    }
}

@Composable
fun DecreaseCountButton(itemQuantity: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(42.dp),
        enabled = itemQuantity > 0 // ✅ Disabled when quantity is 0
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Decrease Count"
        )
    }
}
