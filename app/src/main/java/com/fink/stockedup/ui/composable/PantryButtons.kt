package com.fink.stockedup.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val size = 48.dp

@Composable
fun IncreaseCountButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(size)
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
        modifier = Modifier.size(size),
        enabled = itemQuantity > 0
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Decrease Count"
        )
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
