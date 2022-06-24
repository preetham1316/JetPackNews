package com.android.jetpacknews.feature.articledetail.presentation.model

import com.android.jetpacknews.base.UiState

data class ArticleDetailUiState(
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
) : UiState {
    companion object {
        fun emptyState() = ArticleDetailUiState()
    }
}
