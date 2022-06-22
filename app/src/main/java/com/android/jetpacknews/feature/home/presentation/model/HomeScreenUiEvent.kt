package com.android.jetpacknews.feature.home.presentation.model

import com.android.jetpacknews.base.UiEvent
import com.android.jetpacknews.domain.model.Article

sealed class HomeScreenUiEvent : UiEvent {
    object ShowLoadingProgress : HomeScreenUiEvent()
    data class UpdateQuery(val query: String) : HomeScreenUiEvent()
    data class ShowData(val items: List<Article>) : HomeScreenUiEvent()
}