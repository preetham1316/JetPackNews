package com.android.jetpacknews.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
}