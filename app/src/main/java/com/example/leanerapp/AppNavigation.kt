package com.example.leanerapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = "profile"
    ) {

        composable("profile") {

            ProfileScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle,
                onEditClick = {
                    navController.navigate("edit_profile")
                }
            )
        }

        composable("edit_profile") {

            EditProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}