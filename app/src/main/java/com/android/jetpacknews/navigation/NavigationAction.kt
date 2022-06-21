/*
 * NavigationAction.kt
 * Module: COTO.common.layers.presentation.navigation.controller
 * Project: COTO
 * Copyright © 2022, Eve World Platform PTE LTD. All rights reserved.
 */

package com.android.jetpacknews.navigation

import androidx.navigation.NavOptions

interface NavigationAction {
    val screen: Screen
    val stringArguments: Map<String, String>
        get() = emptyMap()
    val navOptions: NavOptions
        get() = NavOptions.Builder().build() // No NavOptions as default
}

object ScreenAction {
    fun goTo(
        screen: Screen,
        map: Map<String, String> = emptyMap(),
        canLaunchSingleTop: Boolean = true
    ) = object : NavigationAction {
        override val screen: Screen
            get() = screen
        override val stringArguments: Map<String, String>
            get() = map
        override val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(canLaunchSingleTop)
            .build()
    }

    fun goToByClearStack(
        screen: Screen,
        map: Map<String, String> = emptyMap()
    ) = object : NavigationAction {
        override val screen: Screen
            get() = screen
        override val stringArguments: Map<String, String>
            get() = map
        override val navOptions = NavOptions.Builder()
            .setPopUpTo(0, true)
            .setLaunchSingleTop(true)
            .build()
    }

    fun clearStackUpToAndGoToScreen(
        clearStackUpToScreen: Screen,
        destScreen: Screen,
        map: Map<String, String> = emptyMap()
    ) = object : NavigationAction {
        override val screen: Screen
            get() = destScreen
        override val stringArguments: Map<String, String>
            get() = map
        override val navOptions = NavOptions.Builder()
            .setPopUpTo(clearStackUpToScreen.route, false)
            .setLaunchSingleTop(true)
            .build()
    }
}
