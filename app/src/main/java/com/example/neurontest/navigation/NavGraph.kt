package com.example.neurontest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neurontest.ui.screens.registration.components.Registration
import com.example.neurontest.ui.screens.settings.components.SettingsScreen

private object Routes {
    const val SETTINGS = "settings"
    const val REGISTRATION = "registration"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SETTINGS
    ) {
        composable(Routes.SETTINGS) {
            SettingsScreen(
                onNavigateRegister = {
                    navController.navigate(Routes.REGISTRATION) {
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.REGISTRATION) {
            Registration(
                onBack = { navController.popBackStack() },
                onNavigateSettings = {
                    navController.navigate(Routes.SETTINGS) {
                        popUpTo(Routes.SETTINGS) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
    }
}
