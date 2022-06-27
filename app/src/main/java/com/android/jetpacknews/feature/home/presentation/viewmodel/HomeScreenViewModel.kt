package com.android.jetpacknews.feature.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.android.jetpacknews.base.BaseViewModel
import com.android.jetpacknews.base.Reducer
import com.android.jetpacknews.di.coroutine.DispatcherProvider
import com.android.jetpacknews.domain.model.Article
import com.android.jetpacknews.domain.usecase.GetArticlesUseCase
import com.android.jetpacknews.feature.home.presentation.model.HomeScreenState
import com.android.jetpacknews.feature.home.presentation.model.HomeScreenUiEvent
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.navigation.ScreenAction
import com.android.jetpacknews.navigation.ScreenNavigator
import com.android.jetpacknews.navigation.models.BundleKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getArticlesUseCase: GetArticlesUseCase,
    private val screenNavigator: ScreenNavigator
) : BaseViewModel<HomeScreenState, HomeScreenUiEvent>() {
    override val state: StateFlow<HomeScreenState>
        get() = reducer.state

    private val reducer = HomeScreenReducer(HomeScreenState.emptyState())

    fun loadData(query: String) {
        if (query != state.value.query) {  // Fix to avoid reloading page when coming back from second screen
            viewModelScope.launch(dispatcherProvider.main) {
                updateQuery(query)
                sendEvent(HomeScreenUiEvent.ShowLoadingProgress)
                fetchArticles(state.value.query)
            }
        }
    }

    private fun sendEvent(event: HomeScreenUiEvent) {
        reducer.sendEvent(event)
    }

    private fun updateQuery(query: String) {
        reducer.sendEvent(HomeScreenUiEvent.UpdateQuery(query = query))
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
                is HomeScreenUiEvent.UpdateQuery -> setState(oldState.copy(query = event.query))
            }
        }
    }

    private fun fetchArticles(query: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            getArticlesUseCase.invoke(query).fold(onSuccess = {
                sendEvent(HomeScreenUiEvent.ShowData(items = it.articles))
            }, onFailure = {
                // T0D0 handle error event
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

}
