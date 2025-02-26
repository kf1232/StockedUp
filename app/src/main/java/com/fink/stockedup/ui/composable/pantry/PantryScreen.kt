package com.fink.stockedup.ui.composable.pantry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.ui.dialog.PantryEditItemDialog
import com.fink.stockedup.viewmodel.PantryViewModel

@Composable
fun PantryScreen (
    modifier: Modifier,
    viewModel: PantryViewModel,
) {
    val (showAddDialog, selectedItem, openAddDialog, openEditDialog, closeDialogs) = rememberDialogState()
    val pantryItems by viewModel.pantryItems.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = openAddDialog) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Item")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
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