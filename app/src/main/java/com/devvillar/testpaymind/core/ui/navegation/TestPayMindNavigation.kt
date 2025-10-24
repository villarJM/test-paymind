package com.devvillar.testpaymind.core.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devvillar.testpaymind.core.session.SessionViewModel
import com.devvillar.testpaymind.feature.auth.presentation.screens.HomeScreen
import com.devvillar.testpaymind.feature.auth.presentation.screens.LoginScreen
import com.devvillar.testpaymind.feature.transaction.presentation.screens.TransactionScreen

@Composable
fun TestPayMindNavigation(sessionViewModel: SessionViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val isLoggedIn by sessionViewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToTransaction = {
                    navController.navigate(Screen.Transaction.route)
                },
                onLogout = {
                    sessionViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Transaction.route) {
            TransactionScreen(
                onNavigateBack = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Transaction.route) {
                            inclusive = true
                        }
                    }
                }
            )

        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Transaction : Screen("transaction")
}