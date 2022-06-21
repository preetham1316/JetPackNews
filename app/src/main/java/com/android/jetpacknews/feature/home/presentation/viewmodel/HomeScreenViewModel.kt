package com.android.jetpacknews.feature.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.android.jetpacknews.base.BaseViewModel
import com.android.jetpacknews.base.Reducer
import com.android.jetpacknews.di.coroutine.DispatcherProvider
import com.android.jetpacknews.domain.usecase.GetArticlesUseCase
import com.android.jetpacknews.feature.home.presentation.model.HomeScreenState
import com.android.jetpacknews.feature.home.presentation.model.HomeScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel<HomeScreenState, HomeScreenUiEvent>() {
    override val state: StateFlow<HomeScreenState>
        get() = reducer.state

    private val reducer = HomeScreenReducer(HomeScreenState.emptyState())

    init {
        viewModelScope.launch(dispatcherProvider.main) {
            sendEvent(HomeScreenUiEvent.ShowLoadingProgress)
            fetchArticles()
        }
    }

    private fun sendEvent(event: HomeScreenUiEvent) {
        reducer.sendEvent(event)
    }

    private inner class HomeScreenReducer(initialState: HomeScreenState) :
        Reducer<HomeScreenState, HomeScreenUiEvent>(initialState) {
        override fun reduce(oldState: HomeScreenState, event: HomeScreenUiEvent) {
            when (event) {
                is HomeScreenUiEvent.ShowLoadingProgress -> setState(oldState.copy(isLoading = true))
                is HomeScreenUiEvent.ShowData -> setState(
                    oldState.copy(
                        isLoading = false,
                        articleList = event.items
                    )
                )
            }
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch(dispatcherProvider.main) {
            getArticlesUseCase.invoke("Apple").fold(onSuccess = {
                sendEvent(HomeScreenUiEvent.ShowData(items = it.articles))
            }, onFailure = {
                // T0D0 handle error event
            })
        }
    }

}
