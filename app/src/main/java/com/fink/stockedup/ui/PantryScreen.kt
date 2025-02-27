package com.fink.stockedup.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.ui.composable.PantryItemRow
import com.fink.stockedup.ui.composable.pantry.PantryEditItemDialog
import com.fink.stockedup.data.entity.PantryItem
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PantryScreen(
    modifier: Modifier,
    viewModel: PantryViewModel
) {
    val (showAddDialog, selectedItem, openAddDialog, openEditDialog, closeDialogs) = rememberDialogState()
    val pantryItems by viewModel.pantryItems.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = openAddDialog) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Item")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Pantry Items", style = MaterialTheme.typography.headlineMedium)

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(pantryItems) { item ->
                    PantryItemRow(
                        modifier = Modifier,
                        item = item,
                        onDelete = { viewModel.deleteItem(it) },
                        onUpdate = { updatedItem -> viewModel.updateItem(updatedItem) },
                        onLongPress = { openEditDialog(item) }
                    )
                }
            }
        }
    }

    PantryDialogs(
        showAddDialog = showAddDialog,
        selectedItem = selectedItem,
        onDismiss = closeDialogs,
        onAdd = { newItem ->
            viewModel.addItem(newItem)
            closeDialogs()
        },
        onEdit = { updatedItem ->
            viewModel.updateItem(updatedItem)
            closeDialogs()
        }
    )
}

@Composable
private fun PantryDialogs(
    showAddDialog: Boolean,
    selectedItem: PantryItem?,
    onDismiss: () -> Unit,
    onAdd: (PantryItem) -> Unit,
    onEdit: (PantryItem) -> Unit
) {
    if (showAddDialog) {
        PantryAddItemDialog(
            onDismiss = onDismiss,
            onConfirm = onAdd
        )
    }

    selectedItem?.let {
        PantryEditItemDialog(
            existingItem = it,
            onDismiss = onDismiss,
            onConfirm = onEdit
        )
    }
}

@Composable
private fun rememberDialogState(): DialogState {
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<PantryItem?>(null) }

    return DialogState(
        showAddDialog = showAddDialog,
        selectedItem = selectedItem,
        openAddDialog = { showAddDialog = true },
        openEditDialog = { selectedItem = it },
        closeDialogs = { showAddDialog = false; selectedItem = null }
    )
}

private data class DialogState(
    val showAddDialog: Boolean,
    val selectedItem: PantryItem?,
    val openAddDialog: () -> Unit,
    val openEditDialog: (PantryItem) -> Unit,
    val closeDialogs: () -> Unit
)
