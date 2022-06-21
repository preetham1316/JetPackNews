/*
 * ScreenNavigator.kt
 * Module: COTO.common.layers.presentation.navigation.controller
 * Project: COTO
 * Copyright © 2022, Eve World Platform PTE LTD. All rights reserved.
 */

package com.android.jetpacknews.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ScreenNavigator {
    val navActions: StateFlow<NavigationAction?>
    fun navigate(navAction: NavigationAction?)
}

class CustomScreenNavigator : ScreenNavigator {
    private val _navActions: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }
    override val navActions = _navActions.asStateFlow()

    override fun navigate(navAction: NavigationAction?) {
        _navActions.update { navAction }
    }
}
