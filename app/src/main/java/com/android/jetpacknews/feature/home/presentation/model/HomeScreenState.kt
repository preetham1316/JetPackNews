package com.android.jetpacknews.feature.home.presentation.model

import com.android.jetpacknews.base.UiState
import com.android.jetpacknews.domain.model.Article
import javax.annotation.concurrent.Immutable

@Immutable
data class HomeScreenState(
    val isLoading: Boolean = false,
    val articleList: List<Article> = emptyList()
) : UiState {

    companion object {
        fun emptyState() = HomeScreenState()
    }
}