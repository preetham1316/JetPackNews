package com.android.jetpacknews.navigation

import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow

@Composable
fun ScreenRouter(navController: NavHostController, screenNavigator: ScreenNavigator) {
    val currentRoute = navController.currentDestination?.route
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigatorState by screenNavigator.navActions
        .asLifecycleAwareState(
            lifecycleOwner = lifecycleOwner,
            initialState = null
        )
    LaunchedEffect(navigatorState) {
        navigatorState?.let { navigationAction ->
            navigationAction.stringArguments.forEach { arg ->
                navController.currentBackStackEntry?.arguments?.putString(arg.key, arg.value)
            }
            when (navigationAction.screen) {
                is Screen.Back -> {
                    navController.navigateUp()
                }
                else -> {
                    if (currentRoute != navigationAction.screen.route) {
                        if (navigationAction.stringArguments.isEmpty()) {
                            navController.navigate(
                                navigationAction.screen.route,
                                navigationAction.navOptions
                            )
                        } else {
                            navController.navigate(
                                getOptionalStringArgs(
                                    navigationAction.screen.route,
                                    navigationAction.stringArguments
                                ),
                                navigationAction.navOptions
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun <T> Flow<T>.asLifecycleAwareState(lifecycleOwner: LifecycleOwner, initialState: T) =
    lifecycleAwareState(lifecycleOwner, this, initialState)


@Composable
fun <T> lifecycleAwareState(
    lifecycleOwner: LifecycleOwner,
    flow: Flow<T>,
    initialState: T
): State<T> {
    val lifecycleAwareStateFlow = remember(flow, lifecycleOwner) {
        flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    return lifecycleAwareStateFlow.collectAsState(initialState)
}

fun getOptionalStringArgs(route: String, stringArguments: Map<String, String>): String {
    val uri = Uri.parse(route).buildUpon()
    stringArguments.forEach {
        uri.appendQueryParameter(it.key, it.value)
    }
    return uri.toString()
}