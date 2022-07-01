package com.android.jetpacknews.feature.search.presentation.viemodel

import androidx.lifecycle.viewModelScope
import com.android.jetpacknews.base.BaseViewModel
import com.android.jetpacknews.base.Reducer
import com.android.jetpacknews.di.coroutine.DispatcherProvider
import com.android.jetpacknews.domain.model.Article
import com.android.jetpacknews.domain.usecase.SearchArticlesUseCase
import com.android.jetpacknews.feature.search.presentation.model.SearchUiEvent
import com.android.jetpacknews.feature.search.presentation.model.SearchUiState
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.navigation.ScreenAction
import com.android.jetpacknews.navigation.ScreenNavigator
import com.android.jetpacknews.navigation.models.BundleKeys
import com.android.jetpacknews.util.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val screenNavigator: ScreenNavigator,
    private val searchArticlesUseCase: SearchArticlesUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>() {

    init {
        observeSearchQuery()
    }

    override val state: StateFlow<SearchUiState>
        get() = reducer.state

    private val reducer: SearchReducer = SearchReducer(SearchUiState.emptyState())

    val queryFlow = MutableStateFlow(String.EMPTY)

    fun onBackClicked() {
        viewModelScope.launch(dispatcherProvider.main) {
            screenNavigator.navigate(ScreenAction.goTo(Screen.Back()))
        }
    }

    fun onSearchInputChanged(query: String) {
        sendEvent(SearchUiEvent.UpdateQuery(query = query))
        queryFlow.update { query }
    }

    private fun sendEvent(event: SearchUiEvent) {
        reducer.sendEvent(event)
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch(dispatcherProvider.main) {
            queryFlow.debounce(QUERY_DEBOUNCE).collect { query ->
                if (query.isNotEmpty()) search(query) else sendEvent(SearchUiEvent.ClearSearchResults)
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            sendEvent(SearchUiEvent.ShowProgressBar)
            searchArticlesUseCase.invoke(query).fold(onSuccess = {
                sendEvent(SearchUiEvent.ShowData(articles = it.articles))
            }, onFailure = {
                sendEvent(SearchUiEvent.ShowData())
            })
        }
    }

    fun navigateToDetail(article: Article) {
        viewModelScope.launch(dispatcherProvider.main) {
            screenNavigator.navigate(
                ScreenAction.goTo(
                    Screen.ArticleDetail(),
                    mapOf(
                        BundleKeys.TITLE to article.title,
                        BundleKeys.IMAGE_URL to article.urlToImage,
                        BundleKeys.DESCRIPTION to article.description,
                        BundleKeys.AUTHOR to article.author,
                        BundleKeys.PUBLISHED_AT to article.publishedAt
                    )
                )
            )
        }
    }

    fun clearSearchResults() {
        sendEvent(SearchUiEvent.ClearSearchResults)
    }

    private inner class SearchReducer(initial: SearchUiState) :
        Reducer<SearchUiState, SearchUiEvent>(initial) {
        override fun reduce(oldState: SearchUiState, event: SearchUiEvent) {
            when (event) {
                is SearchUiEvent.UpdateQuery -> setState(oldState.copy(query = event.query))
                is SearchUiEvent.ShowProgressBar -> setState(oldState.copy(isLoading = true))
                is SearchUiEvent.ShowData -> setState(
                    SearchUiState(
                        query = oldState.query,
                        isLoading = false,
                        items = event.articles
                    )
                )
                is SearchUiEvent.ClearSearchResults -> setState(SearchUiState())
            }
        }
    }

    companion object {
        const val QUERY_DEBOUNCE = 500L
    }
}
