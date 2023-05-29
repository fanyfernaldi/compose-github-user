package com.example.composegithubuser.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail: Screen("detail/{username}") {
        fun createRoute(username: String) = "detail/$username"
    }
    object Profile: Screen("profile")
}