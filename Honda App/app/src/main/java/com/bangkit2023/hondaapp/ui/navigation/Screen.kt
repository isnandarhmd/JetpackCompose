package com.bangkit2023.hondaapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{motorcycleId}") {
        fun createRoute(motorcycleId: Int) = "home/$motorcycleId"
    }
}
