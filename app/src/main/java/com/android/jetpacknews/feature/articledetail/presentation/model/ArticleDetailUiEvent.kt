package com.android.jetpacknews.feature.articledetail.presentation.model

import com.android.jetpacknews.base.UiEvent
import com.android.jetpacknews.domain.model.Article

sealed class ArticleDetailUiEvent : UiEvent {
    data class InitStateData(val article: Article) : ArticleDetailUiEvent()
    object ShowData : ArticleDetailUiEvent()

}