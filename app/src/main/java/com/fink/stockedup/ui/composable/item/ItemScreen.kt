package com.fink.stockedup.ui.composable.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.viewmodel.ItemViewModel
import kotlinx.coroutines.launch

@Composable
fun ItemScreen(
		modifier: Modifier = Modifier,
		viewModel: ItemViewModel
) {
		val allItems by viewModel.allItems.collectAsState()
		val scope = rememberCoroutineScope()

		var showDialog by remember { mutableStateOf(false) }
		var currentItem by remember { mutableStateOf<Item?>(null) }

		Scaffold(
				modifier = modifier.fillMaxSize(),
				floatingActionButton = {
						FloatingActionButton(onClick = {
								currentItem = null
								showDialog = true
						}) {
								Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Item")
						}
				}
		) { innerPadding ->
				Column(
						modifier = Modifier
								.fillMaxSize()
								.padding(innerPadding)
				) {
						Text(
								text = "Items",
								style = MaterialTheme.typography.headlineMedium,
								modifier = Modifier.padding(16.dp)
						)

						LazyColumn(
								modifier = Modifier.fillMaxSize(),
								contentPadding = PaddingValues(16.dp)
						) {
								items(allItems) { item ->
										ItemRow(
												item = item,
												onEdit = {
														currentItem = it
														showDialog = true
												},
												onDelete = {
														scope.launch { viewModel.deleteItemById(it.itemId) }
												}
										)
								}
						}
				}
		}


		if (showDialog) {
				ItemDialog(
						item = currentItem,
						onDismiss = { showDialog = false },
						onSave = { newItem ->
								scope.launch {
										if (newItem.itemId == 0L) {
												viewModel.addItem(newItem)
										} else {
												viewModel.updateItem(newItem)
										}
								}
								showDialog = false
						}
				)
		}
}

