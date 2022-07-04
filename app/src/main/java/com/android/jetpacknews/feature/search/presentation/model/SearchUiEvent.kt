package com.android.jetpacknews.feature.search.presentation.model

import com.android.jetpacknews.base.UiEvent
import com.android.jetpacknews.domain.model.Article

sealed class SearchUiEvent : UiEvent {
    object ShowProgressBar : SearchUiEvent()
    data class ShowData(val articles: List<Article> = emptyList()) : SearchUiEvent()
    object ClearSearchResults : SearchUiEvent()
}
