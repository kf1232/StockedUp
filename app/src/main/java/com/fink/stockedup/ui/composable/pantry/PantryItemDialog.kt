package com.fink.stockedup.ui.composable.pantry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fink.stockedup.data.entity.Item
import com.fink.stockedup.data.entity.PantryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryItemDialog(
		pantryItem: PantryItem?,
		allItems: List<Item>,
		onDismiss: () -> Unit,
		onSave: (PantryItem) -> Unit
) {
		val focusManager = LocalFocusManager.current

		var selectedItem by remember { mutableStateOf(allItems.find { it.itemId == pantryItem?.itemId }) }
		var pantryItemName by remember { mutableStateOf(pantryItem?.pantryItemName ?: selectedItem?.itemName ?: "") }
		var pantryItemCategory by remember { mutableStateOf(selectedItem?.itemCategory ?: "") }
		var pantryItemUnit by remember { mutableStateOf(selectedItem?.itemUnit ?: "") }
		var pantryItemQuantity by remember { mutableStateOf(pantryItem?.pantryItemQuantity?.toString() ?: "1") }
		var pantryItemNotes by remember { mutableStateOf(pantryItem?.pantryItemNotes ?: "") }
		var pantryItemFavorite by remember { mutableStateOf(pantryItem?.pantryItemFavorite ?: false) }
		var pantryItemExpirationDate by remember { mutableStateOf(pantryItem?.pantryItemExpirationDate?.toString() ?: "") }
		var isDropdownExpanded by remember { mutableStateOf(false) }

		AlertDialog(
				modifier = Modifier,
				title = {
						Text(
							modifier = Modifier,
							text = "Add Pantry Item"
						)
				},
				text = {
						Column {
								if (allItems.isNotEmpty()) {
										Text(text = "Select Existing Item")

										ExposedDropdownMenuBox(
												expanded = isDropdownExpanded,
												onExpandedChange = { isDropdownExpanded = it }
										) {
												OutlinedTextField(
														value = selectedItem?.itemName ?: "Select Item",
														onValueChange = {},
														readOnly = true,
														modifier = Modifier
																.fillMaxWidth()
																.menuAnchor()
																.clickable { isDropdownExpanded = true }
												)

												ExposedDropdownMenu(
														expanded = isDropdownExpanded,
														onDismissRequest = { isDropdownExpanded = false }
												) {
														allItems.forEach { item ->
																DropdownMenuItem(
																		text = { Text(item.itemName) },
																		onClick = {
																				selectedItem = item
																				pantryItemName = item.itemName
																				pantryItemCategory = item.itemCategory
																				pantryItemUnit = item.itemUnit
																				isDropdownExpanded = false
																		}
																)
														}
												}
										}

										Spacer(modifier = Modifier.height(8.dp))
								}

								if (selectedItem != null) {
										Button(
												onClick = {
														selectedItem = null
														pantryItemName = ""
														pantryItemCategory = ""
														pantryItemUnit = ""
												},
												modifier = Modifier.fillMaxWidth()
										) {
												Text("Clear Selection (Create New)")
										}

										HorizontalDivider(
												modifier = Modifier.padding(8.dp),
												thickness = 2.dp,
												color = Color.White
										)
								}



								if (selectedItem == null) {
										// Creating a NEW item - Allow editing of all fields
										OutlinedTextField(
												keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
												keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
												value = pantryItemName,
												onValueChange = { pantryItemName = it },
												label = { Text("Pantry Item Name") }
										)
										Spacer(modifier = Modifier.height(8.dp))
										OutlinedTextField(
												keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
												keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
												value = pantryItemCategory,
												onValueChange = { pantryItemCategory = it },
												label = { Text("Pantry Item Category") }
										)
										Spacer(modifier = Modifier.height(8.dp))
										OutlinedTextField(
												keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
												keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
												value = pantryItemUnit,
												onValueChange = { pantryItemUnit = it },
												label = { Text("Pantry Item Unit") }
										)
								} else {
										// Editing an existing item - Read-only fields
										OutlinedTextField(
												keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
												keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
												value = selectedItem?.itemName ?: "",
												onValueChange = {},
												label = { Text("Item Name") },
												readOnly = true
										)
										Spacer(modifier = Modifier.height(8.dp))
										OutlinedTextField(
												keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
												keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
												value = selectedItem?.itemCategory ?: "",
												onValueChange = {},
												label = { Text("Item Category") },
												readOnly = true
										)
								}

								Spacer(modifier = Modifier.height(8.dp))
								HorizontalDivider(
										modifier = Modifier.padding(8.dp),
										thickness = 2.dp,
										color = Color.White
								)

								// Editable fields for pantry record
								OutlinedTextField(
										keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
										keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
										value = pantryItemQuantity,
										onValueChange = { pantryItemQuantity = it },
										label = { Text("Pantry Item Quantity") }
								)
								Spacer(modifier = Modifier.height(8.dp))

								OutlinedTextField(
										keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
										keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
										value = pantryItemNotes,
										onValueChange = { pantryItemNotes = it },
										label = { Text("Pantry Item Notes") }
								)
								Spacer(modifier = Modifier.height(8.dp))

								// Favorite Toggle
								Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
										Text(text = "Favorite")
										Switch(checked = pantryItemFavorite, onCheckedChange = { pantryItemFavorite = it })
								}

								Spacer(modifier = Modifier.height(8.dp))
								OutlinedTextField(
										keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
										keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
										value = pantryItemExpirationDate,
										onValueChange = { pantryItemExpirationDate = it },
										label = { Text("Expiration Date (YYYY-MM-DD)") }
								)
						}
				},

				confirmButton = {
						TextButton(
								onClick = {
										submitRecord(
												pantryItem,
												selectedItem,
												pantryItemName,
												pantryItemQuantity,
												pantryItemNotes,
												pantryItemFavorite,
												pantryItemExpirationDate,
												onSave,
												onDismiss
										)
								}
						) { Text("Save") }
				},
				dismissButton = {
						TextButton(onClick = { onDismiss() }) { Text("Cancel") }
				},
				onDismissRequest = { onDismiss() },
		)
}

private fun submitRecord(
		pantryItem: PantryItem?,
		selectedItem: Item?,
		pantryItemName: String,
		pantryItemQuantity: String,
		pantryItemNotes: String,
		pantryItemFavorite: Boolean,
		pantryItemExpirationDate: String,
		onSave: (PantryItem) -> Unit,
		onDismiss: () -> Unit
) {
		val newPantryItem = pantryItem?.copy(
				itemId = selectedItem?.itemId ?: 0,
				pantryItemName = pantryItemName,
				pantryItemQuantity = pantryItemQuantity.toInt(),
				pantryItemNotes = pantryItemNotes,
				pantryItemFavorite = pantryItemFavorite,
				pantryItemExpirationDate = pantryItemExpirationDate.toLongOrNull() ?: System.currentTimeMillis()
		) ?: PantryItem(
				itemId = selectedItem?.itemId ?: 0,
				pantryItemName = pantryItemName,
				pantryItemQuantity = pantryItemQuantity.toInt(),
				pantryItemNotes = pantryItemNotes,
				pantryItemFavorite = pantryItemFavorite,
				pantryItemExpirationDate = pantryItemExpirationDate.toLongOrNull() ?: System.currentTimeMillis()
		)

		println("💾 SAVING PantryItem: $newPantryItem")

		onSave(newPantryItem)
		onDismiss()
}