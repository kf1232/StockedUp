package com.fink.stockedup.ui.composable

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.fink.stockedup.ui.composable.home.HomeScreen
import com.fink.stockedup.ui.composable.item.ItemScreen
import com.fink.stockedup.ui.composable.navigation.DrawerContent
import com.fink.stockedup.ui.composable.pantry.PantryScreen
import com.fink.stockedup.ui.composable.settings.SettingsScreen
import com.fink.stockedup.viewmodel.ItemViewModel
import com.fink.stockedup.ui.theme.StockedUpTheme
import com.fink.stockedup.utils.Screen
import com.fink.stockedup.viewmodel.PantryItemViewModel


@Composable
fun MainScreen(
    itemViewModel: ItemViewModel,
    pantryItemViewModel: PantryItemViewModel
) {
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    val allItems by itemViewModel.allItems.collectAsState()
    val allPantryItems by pantryItemViewModel.allPantryItems.collectAsState()

    StockedUpTheme {
        ModalNavigationDrawer(
            drawerContent = {
                DrawerContent(
                    currentScreen = currentScreen,
                    onScreenSelected = { selectedScreen ->
                        currentScreen = selectedScreen
                    }
                )
            }
        ) {
            when (currentScreen) {
                Screen.Home -> HomeScreen(
                    allItems.size,
                    allPantryItems.size
                )
                Screen.Items -> ItemScreen(modifier = Modifier, viewModel = itemViewModel)
                Screen.Pantry -> PantryScreen(
		                modifier = Modifier,
		                itemModel = itemViewModel,
		                pantryItemModel = pantryItemViewModel
                )
                Screen.Settings -> SettingsScreen(viewModel = itemViewModel)
            }
        }
    }
}


