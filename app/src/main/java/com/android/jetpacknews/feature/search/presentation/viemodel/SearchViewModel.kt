package com.android.jetpacknews.feature.search.presentation.viemodel

import androidx.lifecycle.viewModelScope
import com.android.jetpacknews.base.BaseViewModel
import com.android.jetpacknews.base.Reducer
import com.android.jetpacknews.di.coroutine.DispatcherProvider
import com.android.jetpacknews.feature.search.presentation.model.SearchUiEvent
import com.android.jetpacknews.feature.search.presentation.model.SearchUiState
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.navigation.ScreenAction
import com.android.jetpacknews.navigation.ScreenNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val screenNavigator: ScreenNavigator
) : BaseViewModel<SearchUiState, SearchUiEvent>() {

    override val state: StateFlow<SearchUiState>
        get() = reducer.state

    private val reducer: SearchReducer = SearchReducer(SearchUiState.emptyState())

    fun onBackClicked() {
        viewModelScope.launch(dispatcherProvider.main) {
            screenNavigator.navigate(ScreenAction.goTo(Screen.Back()))
        }
    }

    fun onSearchInputChanged(query: String) {
        sendEvent(SearchUiEvent.UpdateQuery(query = query))
    }

    fun sendEvent(event: SearchUiEvent) {
        reducer.sendEvent(event)
    }

    private inner class SearchReducer(initial: SearchUiState) :
        Reducer<SearchUiState, SearchUiEvent>(initial) {
        override fun reduce(oldState: SearchUiState, event: SearchUiEvent) {
            when (event) {
                is SearchUiEvent.UpdateQuery -> setState(oldState.copy(query = event.query))
            }
        }
    }
}
