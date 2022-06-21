package com.android.jetpacknews.feature.splash.presentation.model

import com.android.jetpacknews.base.UiEvent

sealed class SplashScreenUiEvent : UiEvent {
    object ShowLoadingProgress : SplashScreenUiEvent()
    object LaunchHomePage : SplashScreenUiEvent()
}