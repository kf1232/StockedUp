package com.fink.stockedup.ui.composable.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fink.stockedup.viewmodel.ItemViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(viewModel: ItemViewModel) {
		val scope = rememberCoroutineScope()
		var showDialog by remember { mutableStateOf(false) }

		Column(
				modifier = Modifier.fillMaxSize().padding(24.dp),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
		) {
				Text(text = "Settings", style = MaterialTheme.typography.headlineLarge)
				Spacer(modifier = Modifier.height(16.dp))

				Button(
						onClick = { showDialog = true },
						colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
				) {
						Text("Delete All Items", style = MaterialTheme.typography.bodyLarge)
				}
		}

		if (showDialog) {
				AlertDialog(
						onDismissRequest = { showDialog = false },
						title = { Text("Confirm Deletion") },
						text = { Text("This will permanently delete all items. Are you sure?") },
						confirmButton = {
								TextButton(
										onClick = {
												scope.launch { viewModel.deleteAll() }
												showDialog = false
										}
								) { Text("Delete", color = MaterialTheme.colorScheme.error) }
						},
						dismissButton = {
								TextButton(onClick = { showDialog = false }) { Text("Cancel") }
						}
				)
		}
}
