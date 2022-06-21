package com.android.jetpacknews.feature.splash.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.android.jetpacknews.base.BaseViewModel
import com.android.jetpacknews.base.Reducer
import com.android.jetpacknews.di.coroutine.DispatcherProvider
import com.android.jetpacknews.feature.splash.presentation.model.SplashScreenState
import com.android.jetpacknews.feature.splash.presentation.model.SplashScreenUiEvent
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.navigation.ScreenAction
import com.android.jetpacknews.navigation.ScreenNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val screenNavigator: ScreenNavigator,
    dispatcherProvider: DispatcherProvider
) :
    BaseViewModel<SplashScreenState, SplashScreenUiEvent>() {

    override val state: StateFlow<SplashScreenState>
        get() = reducer.state

    private val reducer = SplashScreenReducer(SplashScreenState.emptyState())

    init {
        viewModelScope.launch(dispatcherProvider.main) {
            sendEvent(SplashScreenUiEvent.ShowLoadingProgress)
            delay(1000) // To be removed and add config API
            sendEvent(SplashScreenUiEvent.LaunchHomePage)
        }
    }

    private fun sendEvent(event: SplashScreenUiEvent) {
        reducer.sendEvent(event)
    }

    private inner class SplashScreenReducer(initialState: SplashScreenState) :
        Reducer<SplashScreenState, SplashScreenUiEvent>(initialState) {
        override fun reduce(oldState: SplashScreenState, event: SplashScreenUiEvent) {
            when (event) {
                is SplashScreenUiEvent.ShowLoadingProgress -> setState(oldState.copy(isLoading = true))
                is SplashScreenUiEvent.LaunchHomePage -> screenNavigator.navigate(
                    ScreenAction.goToByClearStack(
                        Screen.Home()
                    )
                )
            }
        }
    }
}