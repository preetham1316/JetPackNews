package com.android.jetpacknews.feature.search.presentation.model

import com.android.jetpacknews.base.UiEvent
import com.android.jetpacknews.domain.model.Article

sealed class SearchUiEvent : UiEvent {
    data class UpdateQuery(val query: String) : SearchUiEvent()
    object ShowProgressBar : SearchUiEvent()
    data class ShowData(val articles: List<Article> = emptyList()) : SearchUiEvent()
    object ClearSearchResults : SearchUiEvent()
}
