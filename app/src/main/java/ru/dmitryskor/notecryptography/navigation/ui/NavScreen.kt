package ru.dmitryskor.notecryptography.navigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.dmitryskor.notecryptography.mainScreen.ui.view.ListNoteScreen
import ru.dmitryskor.notecryptography.navigation.core.ScreenApp

@Composable
fun NavScreen(startScreen: ScreenApp = ScreenApp.MainScreen) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startScreen.name
    ) {
        composable(ScreenApp.MainScreen.name) {
            ListNoteScreen(navController)
        }
    }
}