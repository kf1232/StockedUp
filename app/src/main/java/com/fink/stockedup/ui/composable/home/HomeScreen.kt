package com.fink.stockedup.ui.composable.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(name = "Samsung S Ultra Phone", device = "spec:width=1440dp,height=3200dp,dpi=560", showBackground = true)
@Preview(name = "Samsung S Phone", device = "spec:width=1080dp,height=2340dp,dpi=440", showBackground = true)
@Preview(name = "Samsung A Phone", device = "spec:width=1080dp,height=2400dp,dpi=420", showBackground = true)
@Preview(name = "Samsung X Phone", device = "spec:width=1080dp,height=2340dp,dpi=400", showBackground = true)
@Preview(name = "Samsung S Tablet", device = "spec:width=2560dp,height=1600dp,dpi=320", showBackground = true)
@Preview(name = "Samsung Active Tablet", device = "spec:width=1920dp,height=1200dp,dpi=280", showBackground = true, )
@Composable
fun HomeScreen(
		itemCount: Int = 999,
		pantryItemCount : Int = 999
) {
		Column(
				modifier = Modifier
						.fillMaxSize()
						.padding(24.dp),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
		) {
				Text(text = "Home Page", style = MaterialTheme.typography.headlineLarge)
				Spacer(modifier = Modifier.height(16.dp))

				Card(
						modifier = Modifier.padding(12.dp).fillMaxWidth(),
						elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
				) {
						Column(modifier = Modifier.padding(16.dp)) {
								Text(text = "Total Items", style = MaterialTheme.typography.bodyLarge)
								Text(text = itemCount.toString(), style = MaterialTheme.typography.displayLarge)
						}
				}

				Card(
						modifier = Modifier.padding(12.dp).fillMaxWidth(),
						elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
				) {
						Column(modifier = Modifier.padding(16.dp)) {
								Text(text = "Total Pantry Items", style = MaterialTheme.typography.bodyLarge)
								Text(text = pantryItemCount.toString(), style = MaterialTheme.typography.displayLarge)
						}
				}
		}
}

@Preview(name = "Samsung S Ultra", device = "spec:width=1440dp,height=3200dp,dpi=560", showBackground = true)
@Preview(name = "Samsung S Phone", device = "spec:width=1080dp,height=2340dp,dpi=440", showBackground = true)
@Composable
fun PreviewHomeScreen() {
		//HomeScreen(
		//		itemModel = MockItemViewModel(),
		//		pantryModel = MockPantryItemViewModel() // Create a mock for PantryItemViewModel similarly
		//)
}
