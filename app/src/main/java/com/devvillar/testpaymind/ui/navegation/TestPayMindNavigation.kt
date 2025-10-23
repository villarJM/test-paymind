package com.devvillar.testpaymind.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devvillar.testpaymind.feature.auth.presentation.screens.LoginScreen

@Composable
fun TestPayMindNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {

        }
        composable(Screen.Transaction.route) {

        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Transaction : Screen("transaction")
}