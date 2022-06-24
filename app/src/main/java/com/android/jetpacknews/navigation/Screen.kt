package com.android.jetpacknews.navigation

sealed class Screen(open val route: String) {
    data class None(override val route: String = "") : Screen(route)
    data class Back(override val route: String = "back") : Screen(route)
    data class Splash(override val route: String = "splash") : Screen(route)
    data class Home(override val route: String = "home") : Screen(route)
    data class ArticleDetail(override val route: String = "article_detail") : Screen(route)
}