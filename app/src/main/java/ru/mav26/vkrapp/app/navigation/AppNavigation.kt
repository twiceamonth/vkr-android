package ru.mav26.vkrapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    NavHost(navController = rememberNavController(), startDestination = "") {

    }
}