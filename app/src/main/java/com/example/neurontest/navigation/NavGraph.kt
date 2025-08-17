package com.example.neurontest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neurontest.ui.screens.purchases.Purchases
import com.example.neurontest.ui.screens.registration.Registration
import com.example.neurontest.ui.screens.settings.SettingsScreen

private object Routes {
    const val SETTINGS = "settings"
    const val REGISTRATION = "registration"
    const val PURCHASES = "purchases"
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
                onNavigatePurchases = {
                    navController.navigate(Routes.PURCHASES)
                }
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

        composable("purchases") {
            Purchases(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
