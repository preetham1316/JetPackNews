package com.android.jetpacknews.feature.search.presentation.model

import com.android.jetpacknews.base.UiEvent

sealed class SearchUiEvent : UiEvent {
    data class UpdateQuery(val query: String) : SearchUiEvent()
}
