package com.fink.stockedup.ui.composable.pantry

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.PantryItem
import com.fink.stockedup.viewmodel.ItemViewModel
import com.fink.stockedup.viewmodel.PantryItemViewModel
import kotlinx.coroutines.launch

@Composable
fun PantryScreen(
		modifier: Modifier = Modifier,
		pantryItemModel: PantryItemViewModel,
		itemModel: ItemViewModel
) {
		val allPantryItems by pantryItemModel.allPantryItems.collectAsState(initial = emptyList())
		val allItems by itemModel.allItems.collectAsState(initial = emptyList())
		val scope = rememberCoroutineScope()

		var showDialog by remember { mutableStateOf(false) }
		var currentPantryItem by remember { mutableStateOf<PantryItem?>(null) }

		LaunchedEffect(allItems) {
				println("PantryScreen: allItems size = ${allItems.size}")
		}

		Scaffold(
				modifier = modifier.fillMaxSize(),
				floatingActionButton = {
						FloatingActionButton(onClick = {
								currentPantryItem = null
								showDialog = true
						}) {
								Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Pantry Item")
						}
				}
		) { innerPadding ->
				Column(
						modifier = Modifier
								.fillMaxSize()
								.padding(innerPadding)
				) {
						Text(
								text = "Pantry Items",
								style = MaterialTheme.typography.headlineMedium,
								modifier = Modifier.padding(16.dp)
						)

						LazyColumn(
								modifier = Modifier.fillMaxSize(),
								contentPadding = PaddingValues(16.dp)
						) {
								items(allPantryItems) { pantryItem ->
										PantryItemRow(
												pantryItem = pantryItem,
												onEdit = {
														currentPantryItem = it
														showDialog = true
												},
												onDelete = {
														scope.launch { pantryItemModel.deletePantryItemById(it.pantryItemId) }
												}
										)
								}
						}
				}
		}

		if (showDialog) {
				PantryItemDialog(
						pantryItem = currentPantryItem,
						allItems = allItems,
						onDismiss = { showDialog = false },
						onSave = { newPantryItem ->
								println("PantryItemDialog : Save : $newPantryItem")
								scope.launch {
										if (newPantryItem.pantryItemId == 0L) {
												pantryItemModel.addPantryItem(newPantryItem)
										} else {
												pantryItemModel.updatePantryItem(newPantryItem)
										}
								}
								showDialog = false
						}
				)
		}
}
