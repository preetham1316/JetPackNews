package com.android.jetpacknews.feature.articledetail.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.android.jetpacknews.base.BaseViewModel
import com.android.jetpacknews.base.Reducer
import com.android.jetpacknews.domain.model.Article
import com.android.jetpacknews.feature.articledetail.presentation.model.ArticleDetailUiEvent
import com.android.jetpacknews.feature.articledetail.presentation.model.ArticleDetailUiState
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.navigation.ScreenAction
import com.android.jetpacknews.navigation.ScreenNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val screenNavigator: ScreenNavigator) :
    BaseViewModel<ArticleDetailUiState, ArticleDetailUiEvent>() {

    fun onBackClicked() {
        viewModelScope.launch {
            screenNavigator.navigate(ScreenAction.goTo(Screen.Back()))
        }
    }

    fun setInputParams(
        title: String,
        description: String,
        imageUrl: String,
        author: String,
        publishedAt: String
    ) {
        sendEvent(
            ArticleDetailUiEvent.InitStateData(
                article = Article(
                    title = title,
                    description = description,
                    urlToImage = imageUrl,
                    author = author,
                    publishedAt = publishedAt
                )
            )
        )
    }

    private fun sendEvent(event: ArticleDetailUiEvent) {
        reducer.sendEvent(event)
    }

    private val reducer = ArticleDetailReducer(ArticleDetailUiState.emptyState())

    override val state: StateFlow<ArticleDetailUiState>
        get() = reducer.state

    private inner class ArticleDetailReducer(initial: ArticleDetailUiState) :
        Reducer<ArticleDetailUiState, ArticleDetailUiEvent>(initial) {
        override fun reduce(oldState: ArticleDetailUiState, event: ArticleDetailUiEvent) {
            when (event) {
                is ArticleDetailUiEvent.InitStateData -> {
                    with(event.article) {
                        setState(
                            oldState.copy(
                                title = title,
                                description = description,
                                urlToImage = urlToImage,
                                author = author,
                                publishedAt = publishedAt
                            )
                        )
                    }
                }
            }
        }
    }
}