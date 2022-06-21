package com.android.jetpacknews.feature.splash.presentation.model

import com.android.jetpacknews.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class SplashScreenState(
    val isLoading: Boolean = false
) : UiState {

    companion object {
        fun emptyState() = SplashScreenState()
    }
}