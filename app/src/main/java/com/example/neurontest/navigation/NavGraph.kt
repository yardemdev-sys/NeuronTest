package com.example.neurontest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neurontest.ui.screens.registration.components.Registration
import com.example.neurontest.ui.screens.settings.components.SettingsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "settings"
    ) {
        composable("settings") {
            SettingsScreen(
                onNavigateRegister = { navController.navigate("registration") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("registration") {
            Registration(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
