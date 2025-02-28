package com.fink.stockedup.ui.composable.navigation

import NavigationButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fink.stockedup.utils.Screen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.content.pm.PackageManager
import android.content.Context

@Preview(name = "Samsung S Ultra Phone", device = "spec:width=1440dp,height=3200dp,dpi=560", showBackground = true)
@Preview(name = "Samsung S Phone", device = "spec:width=1080dp,height=2340dp,dpi=440", showBackground = true)
@Preview(name = "Samsung A Phone", device = "spec:width=1080dp,height=2400dp,dpi=420", showBackground = true)
@Preview(name = "Samsung X Phone", device = "spec:width=1080dp,height=2340dp,dpi=400", showBackground = true)
@Preview(name = "Samsung S Tablet", device = "spec:width=2560dp,height=1600dp,dpi=320", showBackground = true)
@Preview(name = "Samsung Active Tablet", device = "spec:width=1920dp,height=1200dp,dpi=280", showBackground = true, )
@Composable
fun DrawerContent(
		currentScreen: Screen = Screen.Home,
		onScreenSelected: (Screen) -> Unit = { }
) {
		val screens = listOf(
				"Home" to Screen.Home,
				"Items" to Screen.Items,
				"Pantry" to Screen.Pantry,
				"Settings" to Screen.Settings
		)

		val context = LocalContext.current
		val versionInfo = remember { getAppVersion(context) }

		Column(
				modifier = Modifier
						.fillMaxSize()
						.background(Color(0xFF1C1C1E).copy(alpha = 0.85f))
						.padding(24.dp),
				verticalArrangement = Arrangement.spacedBy(12.dp),
				horizontalAlignment = Alignment.Start
		) {
				Text(
						text = "Navigation",
						style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
						color = Color.White,
						modifier = Modifier
								.padding(bottom = 24.dp)
								.alpha(0.9f)
				)

				screens.forEach { (title, screen) ->
						NavigationButton(
								text = title,
								selected = currentScreen == screen,
								onClick = { onScreenSelected(screen) }
						)
				}

				Spacer(modifier = Modifier.weight(1f))

				Text(
						text = "Version ${versionInfo.first} (${versionInfo.second})",
						style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
						color = Color(0xFFB0B0B0),
						modifier = Modifier.padding(top = 16.dp)
				)
		}
}

fun getAppVersion(context: Context): Pair<String, Int> {
		return try {
				val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
				Pair(packageInfo.versionName ?: "Unknown", packageInfo.versionCode)
		} catch (e: PackageManager.NameNotFoundException) {
				Pair("Unknown", 0)
		}
}

