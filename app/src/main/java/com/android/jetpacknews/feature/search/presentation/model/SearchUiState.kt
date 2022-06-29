package com.android.jetpacknews.feature.search.presentation.model

import com.android.jetpacknews.base.UiState
import com.android.jetpacknews.domain.model.Article

data class SearchUiState(
    val query: String = "",
    val items: List<Article> = emptyList()
) : UiState {
    companion object {
        fun emptyState() = SearchUiState()
    }
}
